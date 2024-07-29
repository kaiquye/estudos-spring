package com.auth.auth.modules.user.useCases;

import com.auth.auth.infrastructure.exceptions.ResponseHandler;
import com.auth.auth.infrastructure.security.interfaces.ITokenService;
import com.auth.auth.modules.user.UserModel;
import com.auth.auth.modules.user.interfaces.LoginUserInput;
import com.auth.auth.modules.user.interfaces.LoginUserOutput;
import com.auth.auth.modules.user.repositories.IUserRepository;
import com.auth.auth.modules.user.useCases.interfaces.ILoginUserUseCase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCaseImpl implements ILoginUserUseCase {
    private AuthenticationManager authenticationManager;
    private IUserRepository userRepository;
    private ITokenService tokenService;

    public LoginUserUseCaseImpl(AuthenticationManager authenticationManager,  IUserRepository userRepository, ITokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public LoginUserOutput execute(LoginUserInput input) throws Exception {
            var userFound = this.userRepository.findByEmail(input.email());
            if(userFound == null) {
                throw ResponseHandler.notFound("User not found").build();
            }

            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(input.email(), input.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            UserModel userModel = (UserModel) auth.getPrincipal();

            String accessToken = this.tokenService.generate(userModel);
            return new LoginUserOutput(accessToken, userModel.getId());
    }
}
