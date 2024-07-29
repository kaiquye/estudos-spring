package com.auth.auth.modules.user.useCases.interfaces;

import com.auth.auth.modules.user.interfaces.CreateUserInput;
import com.auth.auth.modules.user.interfaces.CreateUserOutput;
import com.auth.auth.shared.UseCase;

public interface ICreateUserUseCase extends UseCase<CreateUserInput, CreateUserOutput> {}
