package co.istad.sakkda.ecommerceapi.feature.category.dto;

import jakarta.validation.constraints.Size;

public record PatchCategoryRequest(
        @Size(max = 50, message = "Name must not exceed 50 characters")
        String name,

        String description,

        String icon
) {
}
