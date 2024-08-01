package com.auth.auth.modules.user.useCases;

import com.auth.auth.infrastructure.exceptions.ResponseHandler;
import com.auth.auth.modules.user.UserModel;
import com.auth.auth.modules.user.enums.ERoles;
import com.auth.auth.modules.user.interfaces.CreateUserInput;
import com.auth.auth.modules.user.interfaces.CreateUserOutput;
import com.auth.auth.modules.user.repositories.IRoleRepository;
import com.auth.auth.modules.user.repositories.IUserRepository;
import com.auth.auth.modules.user.useCases.interfaces.ICreateUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements ICreateUserUseCase {
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public CreateUserOutput execute(CreateUserInput input) throws Exception {
        var userAlreadyExist = this.userRepository.findByEmail(input.email());
        if(userAlreadyExist != null) {
            throw ResponseHandler.notFound("user already exists").addDetails(input.email()).build();
        }

        var defaultRole = this.roleRepository.findByRole(ERoles.DEFAULT);
        UserModel userModel =  UserModel.create(input.name(), input.email(), this.passwordEncoder.encode(input.password()), defaultRole);
        UserModel newUserModel = this.userRepository.save(userModel);

        return new CreateUserOutput(newUserModel.getId());
    }
}
