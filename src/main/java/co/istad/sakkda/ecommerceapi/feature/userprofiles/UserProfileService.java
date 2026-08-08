package co.istad.sakkda.ecommerceapi.feature.userprofiles;

import co.istad.sakkda.ecommerceapi.feature.userprofiles.dto.PatchUserProfileRequest;
import co.istad.sakkda.ecommerceapi.feature.userprofiles.dto.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse getUserProfile();

    UserProfileResponse patchUserProfile(PatchUserProfileRequest patchUserProfileRequest);

}
