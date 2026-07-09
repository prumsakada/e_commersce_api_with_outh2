package co.istad.sakkda.ecommerceapi.feature.fileupload;

import co.istad.sakkda.ecommerceapi.feature.fileupload.dto.FileResponse;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class FileUploadMapper {
//    @Value("${file-upload.base-uri}")
//    private String baseUri;
//
//    @Mapping(source = "uri", target = "uri", qualifiedByName = "buildUri")
//    abstract FileResponse mapFileUploadToFileResponse(FileUpload fileUpload);
//
//    @Named("buildUri")
//    String buildUri(String uri) {
//        return baseUri + "/" + uri;
//    }
}
