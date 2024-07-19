package com.project.ecommerce_platform.models.SignUp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupResponseDto {
    private Long id;
    private String email;
    private String message;
}
