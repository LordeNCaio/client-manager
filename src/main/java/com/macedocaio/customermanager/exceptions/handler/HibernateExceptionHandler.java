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
    public Map<String, List<ErrorMessage>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return mapFieldErros(ex);
    }

    /**
     * Realiza o mapeamento dos erros lançados pelas anotações do jakarta.validation
     * @param ex Exceção lançada quando alguma validação encontra erro
     * @return {@link Map} contendo as mensagens de erro por nome do campo
     */
    private Map<String, List<ErrorMessage>> mapFieldErros(MethodArgumentNotValidException ex) {
        Map<String, List<ErrorMessage>> messagesMap = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            ErrorMessage errorMessage = new ErrorMessage(message);
            createOrAddError(field, errorMessage, messagesMap);
        }
        return messagesMap;
    }

    /**
     * Cria ou adiciona um erro ao mapa de mensagens de erro
     * @param field Nome do campo com erro
     * @param errorMessage Objeto contendo mensagem e horario do erro
     * @param messagesMap {@link Map} contendo as mensagens de erro por nome do campo
     */
    private void createOrAddError(String field, ErrorMessage errorMessage,
                                  Map<String, List<ErrorMessage>> messagesMap) {

        List<ErrorMessage> errors = new ArrayList<>();
        if (messagesMap.containsKey(field)) {
            errors.addAll(messagesMap.get(field));
            errors.add(errorMessage);
        } else {
            errors.add(errorMessage);
        }
        messagesMap.put(field, errors);
    }
}
