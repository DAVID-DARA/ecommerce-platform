package com.project.ecommerce_platform.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VerificationRequestDto {
    private String email;
    private String password;
    private int otp;
}
