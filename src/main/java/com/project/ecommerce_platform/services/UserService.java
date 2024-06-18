package com.project.ecommerce_platform.services;

import com.project.ecommerce_platform.entities.User;
import com.project.ecommerce_platform.models.SignupRequestDto;
import com.project.ecommerce_platform.models.SignupResponseDto;
import com.project.ecommerce_platform.repositories.UserRepository;
import com.project.ecommerce_platform.utilities.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<SignupResponseDto> signup (SignupRequestDto signupRequestDto)throws Exception {
        SignupResponseDto signupResponseDto = new SignupResponseDto();

        Optional<User> requiredUser = userRepository.findByEmail(signupRequestDto.getEmail());
        if (requiredUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .header("Content-Type", "Application/JSON")
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

            Optional<User> CREATED_NEW_USER = userRepository.findByEmail(signupRequestDto.getEmail());
            User user = CREATED_NEW_USER.get();
            Long CREATE_NEW_USERID = user.getUserId();

            signupResponseDto.setId(CREATE_NEW_USERID);
            signupResponseDto.setEmail(newUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "Application/JSON")
                .body(signupResponseDto);
    }
}
