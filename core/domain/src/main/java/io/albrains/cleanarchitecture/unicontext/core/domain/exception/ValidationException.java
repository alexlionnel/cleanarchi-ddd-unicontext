package io.albrains.cleanarchitecture.unicontext.core.domain.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
