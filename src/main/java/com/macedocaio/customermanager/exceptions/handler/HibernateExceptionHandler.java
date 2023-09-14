package com.macedocaio.customermanager.exceptions.handler;

import com.macedocaio.customermanager.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class HibernateExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, List<ErrorMessage>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, List<ErrorMessage>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            ErrorMessage errorMessage = new ErrorMessage(error.getDefaultMessage());

            List<ErrorMessage> errosList = new ArrayList<>();
            if (errors.containsKey(fieldName)) {
                errosList.addAll(errors.get(fieldName));
                errosList.add(errorMessage);
            } else {
                errosList.add(errorMessage);
                errors.put(fieldName, errosList);
            }
            errors.put(fieldName, errosList);
        });
        return errors;
    }
}
