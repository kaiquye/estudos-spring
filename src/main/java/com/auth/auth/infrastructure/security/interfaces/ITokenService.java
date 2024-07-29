package com.auth.auth.infrastructure.security.interfaces;

import com.auth.auth.modules.user.UserModel;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Optional;

public interface ITokenService {

    public String generate(UserModel userModel);
    public Optional<DecodedJWT> compare(String accessToken);
}
