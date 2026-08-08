package co.istad.sakkda.ecommerceapi.feature.userprofiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository
extends JpaRepository<UserProfile, String> {
}
