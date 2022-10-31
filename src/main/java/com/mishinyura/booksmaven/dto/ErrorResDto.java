package com.mishinyura.booksmaven.dto;

public class ErrorResDto {
    private String message;

    public ErrorResDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
