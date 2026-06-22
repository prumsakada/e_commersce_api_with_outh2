package co.istad.sakkda.ecommerceapi.feature.category.dto;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon
) {
}
