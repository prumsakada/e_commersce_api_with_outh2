package co.istad.sakkda.ecommerceapi.feature.userprofiles.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PatchUserProfileRequest(
        @Size(max = 255)
        String firstName,
        @Size(max = 255)
        String lastName,
        @Size(max = 25)
        String gender,
        @Size(max = 255)
        String biography,
        @Size(max = 255)
        String profilePicture,
        @Size(max = 255)
        String jobTitle,
        @Positive
        BigDecimal salary,
        @Size(max = 255)
        String phoneNumber,
        @Size(max = 255)
        String githubLink,
        @Size(max = 255)
        String facebookLink
) {
}
