package com.nester.Rew.service.exception;

public class RewException extends RuntimeException {
    public RewException() {
    }

    public RewException(String message) {
        super(message);
    }

    public RewException(String message, Throwable cause) {
        super(message, cause);
    }

    public RewException(Throwable cause) {
        super(cause);
    }
}
