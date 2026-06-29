package co.istad.sakkda.ecommerceapi.feature.fileupload;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileuploadReposity extends
        JpaRepository<Fileupload, Integer> {
}
