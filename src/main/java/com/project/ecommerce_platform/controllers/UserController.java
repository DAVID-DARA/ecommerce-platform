package com.project.ecommerce_platform.controllers;

import com.project.ecommerce_platform.models.Login.LoginRequestDto;
import com.project.ecommerce_platform.models.SignUp.SignupRequestDto;
import com.project.ecommerce_platform.models.SignUp.SignupResponseDto;
import com.project.ecommerce_platform.models.VerificationRequestDto;
import com.project.ecommerce_platform.services.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup (@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody VerificationRequestDto verificationRequestDto) {
        return userService.verifyUser(verificationRequestDto);
    }
}
