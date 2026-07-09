package co.istad.sakkda.ecommerceapi.feature.fileupload;

import co.istad.sakkda.ecommerceapi.feature.fileupload.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileuploadReposity fileuploadReposity;
    private final FileUploadMapper fileUploadMapper;

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Value("${file-upload.base-uri}")
    private String baseUri;


    @Override
    public Page<FileResponse> getFiles(FileUpload fileUpload, Pageable pageable) {
//        return fileuploadReposity.findAll(pageable)
//                .map(fileUploadMapper::mapFileUploadToFileResponse);
        return null;
    }

    @Override
    public FileResponse findByName(String name) {
//        return fileuploadReposity
//                .findByName(name)
//                .map(fileUploadMapper::mapFileUploadToFileResponse)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND,
//                        "File has not been found"
//                ));
        return null;
    }

    @Override
    public List<FileResponse> UploadMultipartFiles(List<MultipartFile> files) {
        return files.stream()
                .map(this::saveFile)
                .collect(Collectors.toList());
    }

    @Override
    public FileResponse upload(MultipartFile file) {
       return saveFile(file);
    }

    private FileResponse saveFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        String fileExt = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        Path path = Paths.get(String.format("%s%s.%s", serverPath, fileName, fileExt));
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Fileupload fileupload = new Fileupload();
        fileupload.setName(fileName);
        fileupload.setExtension(fileExt);
        fileupload.setMediaType(file.getContentType());
        fileupload.setSize(file.getSize());

        fileuploadReposity.save(fileupload);

        return FileResponse.builder()
                .name(fileupload.getName())
                .extension(fileupload.getExtension())
                .mediaType(fileupload.getMediaType())
                .size(fileupload.getSize())
                .uri(baseUri +"/"+ fileupload.getName() + "."+ fileupload.getExtension())
                .build();
    }
}
