package co.istad.sakkda.ecommerceapi.feature.fileupload;

import co.istad.sakkda.ecommerceapi.feature.fileupload.dto.FileResponse;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {

    Page<FileResponse> getFiles(FileUpload fileUpload, Pageable pageable);

    FileResponse findByName(String name);

    List<FileResponse> UploadMultipartFiles(List<MultipartFile> files) ;

    FileResponse upload(MultipartFile file);

}
