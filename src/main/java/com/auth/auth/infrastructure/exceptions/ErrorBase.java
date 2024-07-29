package com.auth.auth.infrastructure.exceptions;

import java.util.Optional;

public record ErrorBase(String message, String details, int errorId) {}
