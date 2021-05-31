package com.example.be.dto.reponse;

public class ReponseMessage {
    private String message;

    public ReponseMessage() {
    }

    public ReponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
