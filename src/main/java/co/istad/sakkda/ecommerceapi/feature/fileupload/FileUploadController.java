package co.istad.sakkda.ecommerceapi.feature.fileupload;

import co.istad.sakkda.ecommerceapi.feature.fileupload.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/multiple")
    public List<FileResponse> UploadMultipleFiles(
            @RequestPart List<MultipartFile> files) {
        return fileUploadService.UploadMultipartFiles(files);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileResponse upload(@RequestPart MultipartFile file) {
        return fileUploadService.upload(file);
    }

}
