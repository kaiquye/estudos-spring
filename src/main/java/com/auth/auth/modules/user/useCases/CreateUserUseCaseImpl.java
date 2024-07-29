package com.auth.auth.modules.user.useCases;

import com.auth.auth.infrastructure.exceptions.ResponseHandler;
import com.auth.auth.modules.user.UserModel;
import com.auth.auth.modules.user.interfaces.CreateUserInput;
import com.auth.auth.modules.user.interfaces.CreateUserOutput;
import com.auth.auth.modules.user.repositories.IUserRepository;
import com.auth.auth.modules.user.useCases.interfaces.ICreateUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements ICreateUserUseCase {
    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserOutput execute(CreateUserInput input) throws Exception {
        var userAlreadyExist = this.userRepository.findByEmail(input.email());
        if(userAlreadyExist != null) {
            throw ResponseHandler.notFound("user already exists").addDetails(input.email()).build();
        }

        UserModel userModel = new UserModel(input.name(), input.email(), this.passwordEncoder.encode(input.password()));
        UserModel newUserModel = this.userRepository.save(userModel);

        return new CreateUserOutput(newUserModel.getId());
    }
}
