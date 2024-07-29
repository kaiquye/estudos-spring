package com.auth.auth.shared;

public interface UseCase<Input, Output> {
    abstract Output execute(Input input) throws Exception;
}
