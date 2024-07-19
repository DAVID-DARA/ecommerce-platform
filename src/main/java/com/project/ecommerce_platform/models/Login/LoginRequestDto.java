package com.project.ecommerce_platform.models.Login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDto {

    @Email
    private String email;

    @NotBlank
    @NotNull(message = "Please provide your lastname")
    @Size(min =8, max = 16)
    private String password;
}
