package com.macedocaio.clientmanager.exceptions.handler;

import com.macedocaio.clientmanager.exceptions.ErrorMessage;
import com.macedocaio.clientmanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.clientmanager.exceptions.customer.UsernameAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleUsernameAlreadyInUseException(UsernameAlreadyInUseException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(CpfAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleCpfAlreadyInUseException(CpfAlreadyInUseException exception) {
        return new ErrorMessage(exception.getMessage());
    }
}
