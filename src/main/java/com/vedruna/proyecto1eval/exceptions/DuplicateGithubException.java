package com.vedruna.proyecto1eval.exceptions;
public class DuplicateGithubException extends RuntimeException {
    public DuplicateGithubException(String message) {
        super(message);
    }
}