package io.albrains.cleanarchitecture.unicontext.restapi.exception.handler;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BankAccountExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleValidationException(ValidationException validationException) {
        return new ErrorDTO(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                validationException.getMessage()
        );
    }
}
