package co.istad.sakkda.ecommerceapi.feature.fileupload;

import co.istad.sakkda.ecommerceapi.feature.fileupload.dto.FileResponse;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    FileResponse upload(MultipartFile file);

}
