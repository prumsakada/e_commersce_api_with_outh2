package co.istad.sakkda.ecommerceapi.feature.fileupload;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileuploadReposity extends
        JpaRepository<Fileupload, Integer> {

    Optional<Fileupload> findByName(String filename);

}
