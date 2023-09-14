package com.macedocaio.customermanager.exceptions;

import java.time.LocalDateTime;

/**
 * @author caiom
 * Classe utilizada para construção de mensagens de erro
 */
public final class ErrorMessage {

    /**
     * Mensagem que será exibida no caso de algum erro
     */
    private final String message;

    /**
     * Data e Hora em que o erro foi gerado
     */
    private final LocalDateTime timestamp;

    /**
     * Cria uma instância da classe {@link ErrorMessage}
     * @param message Mensagem do erro que será retornada para o usuário
     */
    public ErrorMessage(String message) {
        this.message = message;
        timestamp = LocalDateTime.now();
    }

    /**
     * Cria uma instância da classe {@link ErrorMessage}
     * @param message Mensagem do erro que será retornada para o usuário
     * @param timestamp Data e Hora em que o erro aconteceu
     */
    public ErrorMessage(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
