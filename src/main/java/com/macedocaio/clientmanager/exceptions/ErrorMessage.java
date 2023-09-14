package com.macedocaio.clientmanager.exceptions;

import java.time.LocalDateTime;

public final class ErrorMessage {

    private final String message;
    private final LocalDateTime timestamp;

    public ErrorMessage(String message) {
        this.message = message;
        timestamp = LocalDateTime.now();
    }

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
