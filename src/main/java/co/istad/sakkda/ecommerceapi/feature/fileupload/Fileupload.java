package co.istad.sakkda.ecommerceapi.feature.fileupload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "files")
public class Fileupload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false , length = 10)
    private String extension;
    @Column(nullable = false , length = 15)
    private String mediaType;
    @Column(nullable = false)
    private Long size;

}
