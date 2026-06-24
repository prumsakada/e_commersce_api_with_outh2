package co.istad.sakkda.ecommerceapi.feature.fileupload.dto;

import lombok.Builder;

@Builder
public record FileResponse(
        String name,
        Long size,
        String mediaType,
        String uri
) {
}
