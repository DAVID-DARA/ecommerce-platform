package com.project.ecommerce_platform.models.Auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VerificationResponseDto {

    private String token;
    private String message;
}
