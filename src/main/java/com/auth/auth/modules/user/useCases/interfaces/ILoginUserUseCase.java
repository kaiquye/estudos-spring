package com.auth.auth.modules.user.useCases.interfaces;

import com.auth.auth.modules.user.interfaces.LoginUserInput;
import com.auth.auth.modules.user.interfaces.LoginUserOutput;
import com.auth.auth.shared.UseCase;

public interface ILoginUserUseCase extends UseCase<LoginUserInput, LoginUserOutput> {
}
