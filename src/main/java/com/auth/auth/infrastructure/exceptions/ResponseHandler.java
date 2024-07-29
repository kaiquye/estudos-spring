package com.auth.auth.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class ResponseHandler extends Exception {
    private int errorId;
    private String details;
    private HttpStatus statusCode;

    public ResponseHandler(Builder builder) {
        super(builder.message);
        this.details = builder.details;
        this.errorId = builder.errorId;
        this.statusCode = builder.statusCode;
    }

    public static class Builder {
        private int errorId;
        private String message;
        private String details;
        private HttpStatus statusCode;

        public Builder(String message, HttpStatus statusCode) {
            this.message = message;
            this.statusCode = statusCode;
            this.errorId = 0;
        }

        public Builder addDetails(String details) {
            this.details = details;
            return this;
        }
        public Builder addErrorId(int errorId) {
            this.errorId = errorId;
            return this;
        }
        public Builder alterStatusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ResponseHandler build() {
            return new ResponseHandler(this);
        }
    }

    public static Builder notFound(String message) {
        return new Builder(message, HttpStatus.NOT_FOUND);
    }

    public static Builder conflict(String message) {
        return new Builder(message, HttpStatus.CONFLICT);
    }

    public static Builder badRequest(String message) {
        return new Builder(message, HttpStatus.BAD_REQUEST);
    }

    public static Builder unauthorized(String message) {
        return new Builder(message, HttpStatus.UNAUTHORIZED);
    }

    public static Builder forbidden(String message) {
        return new Builder(message, HttpStatus.FORBIDDEN);
    }

    public static Builder internalServerError(String message) {
        return new Builder(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getDetails() {
        return details;
    }
    public int getErrorId() {
        return errorId;
    }
    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
