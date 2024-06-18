package com.project.ecommerce_platform.controllers;

import com.project.ecommerce_platform.models.SignupRequestDto;
import com.project.ecommerce_platform.models.SignupResponseDto;
import com.project.ecommerce_platform.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup (@RequestBody SignupRequestDto signupRequestDto) throws Exception {
        return userService.signup(signupRequestDto);
    }
}
