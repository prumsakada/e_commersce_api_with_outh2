package co.istad.sakkda.ecommerceapi.feature.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 255)
        String username,
        @NotBlank(message = "Password is required")
        @Size(max = 255)
        String password,
        @NotBlank(message = "Confirmed password is required")
        @Size(max = 255)
        String confirmedPassword,
        @NotBlank(message = "Email is required")
        @Size(max = 255)
        @Email
        String email,
        @Size(max = 255)
        @NotBlank(message = "First name is required")
        String firstName,
        @Size(max = 255)
        @NotBlank(message = "Last name is required")
        String lastName,
        @Size(max = 255)
        String gender,
        @Size(max = 255)
        String biography
) {
}
