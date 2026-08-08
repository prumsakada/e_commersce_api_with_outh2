package co.istad.sakkda.ecommerceapi.feature.userprofiles;

import co.istad.sakkda.ecommerceapi.feature.userprofiles.dto.PatchUserProfileRequest;
import co.istad.sakkda.ecommerceapi.feature.userprofiles.dto.UserProfileResponse;
import co.istad.sakkda.ecommerceapi.security.AuthUtil;
import co.istad.sakkda.ecommerceapi.security.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse patchUserProfile(PatchUserProfileRequest patchUserProfileRequest) {

        String userId = AuthUtil.extractUserId();
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User profile has not been found"
                ));
        userProfileMapper.toEntity(userProfile, patchUserProfileRequest);
        userProfileRepository.save(userProfile);
        UserResource userResource = keycloak.realm(keycloakProperties.getRealm())
                .users()
                .get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userProfileMapper.toUserRepresentation(userRepresentation, patchUserProfileRequest);
        userResource.update(userRepresentation);

        return userProfileMapper.buildUserProfileResponse(userRepresentation,userProfile);
    }

    @Override
    public UserProfileResponse getUserProfile() {

        String userId = AuthUtil.extractUserId();
        UserResource userResource = keycloak.realm(keycloakProperties.getRealm())
                .users()
                .get(userId);

        UserRepresentation userRepresentation = userResource.toRepresentation();
        log.info("user profile: {}", userRepresentation);

        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User profile has not been found"
                ));


        return userProfileMapper.buildUserProfileResponse(userRepresentation, userProfile);
    }

}
