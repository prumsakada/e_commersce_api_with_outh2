package co.istad.sakkda.ecommerceapi.feature.userprofiles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    private String UserId;
    private String profilePicture;
    private String jobTitle;
    private BigDecimal salary;
    private String phoneNumber;
    private String githubLink;
    private String facebookLink;
}
