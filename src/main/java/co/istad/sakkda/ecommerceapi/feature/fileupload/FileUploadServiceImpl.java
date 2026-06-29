package co.istad.sakkda.ecommerceapi.feature.fileupload;

import co.istad.sakkda.ecommerceapi.feature.fileupload.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileuploadReposity fileuploadReposity;

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Value("${file-upload.base-uri}")
    private String baseUri;


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
