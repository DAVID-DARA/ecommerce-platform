package com.project.ecommerce_platform.services.user;

import com.project.ecommerce_platform.models.SignUp.SignupRequestDto;
import com.project.ecommerce_platform.models.SignUp.SignupResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
        ResponseEntity<SignupResponseDto> signup(SignupRequestDto signupRequestDto) throws Exception;
}
