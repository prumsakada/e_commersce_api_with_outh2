package co.istad.sakkda.ecommerceapi.feature.product.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String name,
        BigDecimal price,
        Integer qty,
        String description,
        Boolean isAvailable,
        String categoryName
) {
}
