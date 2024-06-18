package com.project.ecommerce_platform.services.user;

import com.project.ecommerce_platform.entities.User;
import com.project.ecommerce_platform.exceptions.UserCreationException;
import com.project.ecommerce_platform.models.SignupRequestDto;
import com.project.ecommerce_platform.models.SignupResponseDto;
import com.project.ecommerce_platform.repositories.UserRepository;
import com.project.ecommerce_platform.utilities.Roles;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<SignupResponseDto> signup (SignupRequestDto signupRequestDto) {
        SignupResponseDto signupResponseDto = new SignupResponseDto();

        Optional<User> existingUser = userRepository.findByEmail(signupRequestDto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Content-Type", "application/json")
                    .body(signupResponseDto);
        }

        User newUser = new User();
        newUser.setEmail(signupRequestDto.getEmail());
        newUser.setFirst_name(signupRequestDto.getFirstName());
        newUser.setLast_name(signupRequestDto.getLastName());
        newUser.setPassword(signupRequestDto.getPassword());
        newUser.setRole(Roles.REGULAR);

        try{
            userRepository.save(newUser);

            User savedUser = userRepository.save(newUser);
            signupResponseDto.setId(savedUser.getUserId());
            signupResponseDto.setEmail(savedUser.getEmail());

            logger.info("New user successfully created with email: {}", savedUser.getEmail());
        } catch (Exception e) {
            logger.error("Error creating new user", e);
            throw new UserCreationException("Error creating new user", e);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(signupResponseDto);
    }
}
