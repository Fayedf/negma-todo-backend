package com.negma.DTO.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "the name is required")
    @Size(max = 120, min = 3 , message = "the name must be at least ${min} and not exceed 120 character ")
    private String fullName;

    @NotBlank(message = "the name is required")
    @Email(message = "Email is invalid")
    @Size(max = 320, message = "the email must not exceed 120 character ")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(
            min = 8,
            max = 72,
            message = "Password must be between 8 and 72 characters"
    )
    private String password;



}
