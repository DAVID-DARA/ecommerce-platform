package com.project.ecommerce_platform.models.SignUp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Parameter;

@Getter
@Setter
@ToString
public class SignupRequestDto {

    @NotBlank
    @NotNull(message = "Please provide your firstname")
    @Size(min =3, max = 20)
    private String firstName;

    @NotBlank
    @NotNull(message = "Please provide your lastname")
    @Size(min =3, max = 20)
    private String lastName;

    @Email
    private String email;

    @NotBlank
    @NotNull(message = "Please provide your password")
    @Size(min =8, max = 16)
    private String password;
}