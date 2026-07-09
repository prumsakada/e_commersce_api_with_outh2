package co.istad.sakkda.ecommerceapi.feature.auth;

import co.istad.sakkda.ecommerceapi.feature.auth.dto.RegisterResponse;
import co.istad.sakkda.ecommerceapi.feature.auth.dto.RegisterRequest;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);

}
