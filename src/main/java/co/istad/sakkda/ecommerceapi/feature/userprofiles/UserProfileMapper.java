package co.istad.sakkda.ecommerceapi.feature.userprofiles;

import co.istad.sakkda.ecommerceapi.feature.userprofiles.dto.PatchUserProfileRequest;
import co.istad.sakkda.ecommerceapi.feature.userprofiles.dto.UserProfileResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserProfileMapper {

    public void toUserRepresentation(
            UserRepresentation userRepresentation,
            PatchUserProfileRequest patchUserProfileRequest)
    {
        if (patchUserProfileRequest == null)
        {
            return;
        }
        if (patchUserProfileRequest.firstName() != null)
        {
            userRepresentation.setFirstName(patchUserProfileRequest.firstName());
        }
        if (patchUserProfileRequest.lastName() != null)
        {
            userRepresentation.setLastName(patchUserProfileRequest.lastName());
        }
        if (patchUserProfileRequest.gender() != null)
        {
            userRepresentation.getAttributes().get("gender")
                    .set(0, patchUserProfileRequest.gender());
        }
        if (patchUserProfileRequest.biography() != null)
        {
            userRepresentation.getAttributes().get("biography")
                    .set(0, patchUserProfileRequest.biography());
        }
        if (patchUserProfileRequest.phoneNumber() != null)
        {
            userRepresentation.getAttributes().get("phoneNumber")
                    .set(0, patchUserProfileRequest.phoneNumber());
        }
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract UserProfile toEntity(@MappingTarget UserProfile userProfile,
            PatchUserProfileRequest patchUserProfileRequest);

    public UserProfileResponse buildUserProfileResponse(
            UserRepresentation userRepresentation,
            UserProfile userProfile
    ){
        return UserProfileResponse.builder()
                .userId(userRepresentation.getId())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .email(userRepresentation.getEmail())
                .gender(userRepresentation.getAttributes().get("gender").getFirst())
                .biography(userRepresentation.getAttributes().get("biography").getFirst())
                .profilePicture(userProfile.getProfilePicture())
                .phoneNumber(userProfile.getPhoneNumber())
                .jobTitle(userProfile.getJobTitle())
                .salary(userProfile.getSalary())
                .facebookLink(userProfile.getFacebookLink())
                .githubLink(userProfile.getGithubLink())
                .build();
    }

}
