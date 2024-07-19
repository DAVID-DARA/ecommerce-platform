package com.project.ecommerce_platform.services.auth;

import com.project.ecommerce_platform.models.Auth.JwtAuthenticationResponse;
import com.project.ecommerce_platform.models.Login.LoginRequestDto;
import com.project.ecommerce_platform.models.SignUp.SignupRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<JwtAuthenticationResponse> signup(SignupRequestDto request);

    ResponseEntity<JwtAuthenticationResponse> login(LoginRequestDto loginRequest);
}