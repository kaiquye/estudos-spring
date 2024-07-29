package com.auth.auth.infrastructure.security.interfaces;

import com.auth.auth.modules.user.User;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Optional;

public interface ITokenService {

    public String generate(User user);
    public Optional<DecodedJWT> compare(String accessToken);
}
