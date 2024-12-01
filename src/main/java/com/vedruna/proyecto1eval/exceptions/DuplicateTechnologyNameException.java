package com.vedruna.proyecto1eval.exceptions;

public class DuplicateTechnologyNameException extends RuntimeException {
    public DuplicateTechnologyNameException(String message) {
        super(message);
    }
}