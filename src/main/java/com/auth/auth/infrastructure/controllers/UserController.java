package com.auth.auth.infrastructure.controllers;

import com.auth.auth.infrastructure.controllers.Dtos.LoginUserDto;
import com.auth.auth.infrastructure.controllers.Dtos.CreateUserDto;
import com.auth.auth.modules.user.interfaces.CreateUserInput;
import com.auth.auth.modules.user.interfaces.CreateUserOutput;
import com.auth.auth.modules.user.interfaces.LoginUserInput;
import com.auth.auth.modules.user.interfaces.LoginUserOutput;
import com.auth.auth.modules.user.useCases.interfaces.ICreateUserUseCase;
import com.auth.auth.modules.user.useCases.interfaces.ILoginUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private ICreateUserUseCase createUserUseCase;
    private ILoginUserUseCase loginUserUseCase;

    public UserController(ICreateUserUseCase createUserUseCase, ILoginUserUseCase loginUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody(required = true) CreateUserDto request) throws Exception {
        CreateUserInput input = new CreateUserInput(request.name(), request.email(), request.password());
        CreateUserOutput output = this.createUserUseCase.execute(input);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody(required = true) LoginUserDto request) throws Exception {
        LoginUserInput input = new LoginUserInput(request.email(), request.password());
        LoginUserOutput output = this.loginUserUseCase.execute(input);
        return new ResponseEntity(output, HttpStatus.OK);
    }
}
