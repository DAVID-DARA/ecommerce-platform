package com.project.ecommerce_platform.services.user;

import com.project.ecommerce_platform.entities.Otp;
import com.project.ecommerce_platform.entities.User;
import com.project.ecommerce_platform.exceptions.UserCreationException;
import com.project.ecommerce_platform.helpers.OTP_Generator;
import com.project.ecommerce_platform.models.Auth.JwtAuthenticationResponse;
import com.project.ecommerce_platform.models.Login.LoginRequestDto;
import com.project.ecommerce_platform.models.Login.LoginResponseDto;
import com.project.ecommerce_platform.models.SignUp.SignupRequestDto;
import com.project.ecommerce_platform.models.SignUp.SignupResponseDto;
import com.project.ecommerce_platform.models.VerificationRequestDto;
import com.project.ecommerce_platform.repositories.OtpRepository;
import com.project.ecommerce_platform.repositories.UserRepository;
import com.project.ecommerce_platform.services.EmailService;
import com.project.ecommerce_platform.services.auth.JwtService;
import com.project.ecommerce_platform.utilities.Roles;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;

    private final JwtService jwtService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public ResponseEntity<SignupResponseDto> signup (SignupRequestDto signupRequestDto) {
        SignupResponseDto signupResponseDto = new SignupResponseDto();

        Optional<User> existingUser = userRepository.findByEmail(signupRequestDto.getEmail());
        if (existingUser.isPresent()) {
            signupResponseDto.setMessage("User already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Content-Type", "application/json")
                    .body(signupResponseDto);
        }

        int generatedOtp = OTP_Generator.generateToken();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);

        User newUser = new User();
        newUser.setEmail(signupRequestDto.getEmail());
        newUser.setFirst_name(signupRequestDto.getFirstName());
        newUser.setLast_name(signupRequestDto.getLastName());
        newUser.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        newUser.setRole(Roles.REGULAR);

        Otp otpEntity = new Otp();
        otpEntity.setUser(newUser);
        otpEntity.setOtp(generatedOtp);
        otpEntity.setExpirationTime(expirationTime);

        try {
            User savedUser = userRepository.save(newUser);
            signupResponseDto.setId(savedUser.getUserId());
            signupResponseDto.setEmail(savedUser.getEmail());
            signupResponseDto.setMessage("User successfully created");

            logger.info("New user successfully created with email: {}", savedUser.getEmail());
            try {
                otpRepository.save(otpEntity);
                logger.info("OTP saved successfully");
                try {
                    emailService.sendRegEmail(savedUser.getEmail(), savedUser.getFirst_name(), otpEntity.getOtp());
                    logger.info("Mail Sent successfully to user");
                } catch (Exception e) {
                    logger.error("Error sending email: {}", e.getMessage());
                }
            } catch (Exception e) {
                logger.error("Error saving new user OTP", e);
            }
        } catch (Exception e) {
            logger.error("Error creating new user", e);
            throw new UserCreationException("Error creating new user", e);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(signupResponseDto);
    }

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        System.out.println(jwtToken);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(loginResponse);
    }

    @Override
    public ResponseEntity<?> verifyUser(VerificationRequestDto verificationRequest) {
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();

        Optional<User> userOptional = userRepository.findByEmail(verificationRequest.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        Optional<Otp> otpOptional = otpRepository.findByUser(user);
        if (otpOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OTP not found");
        }

        Otp verificationOtp = otpOptional.get();
        if (verificationOtp.getOtp() == verificationRequest.getOtp()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, user.getPassword(), userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtService.generateToken(userDetails);
            authenticationResponse.setToken(jwtToken);
            logger.info("Verification successful {}: (redirect)", user.getEmail());
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(authenticationResponse);
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("message: Wrong Otp");
        }
    }

}
