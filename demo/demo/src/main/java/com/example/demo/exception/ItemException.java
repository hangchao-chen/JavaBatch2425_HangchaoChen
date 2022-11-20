package com.example.demo.exception;

public abstract class ItemException extends RuntimeException {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public ItemException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ItemException() {
        super();
    }
}
