package ru.chudakov.web.form;

public class Error extends Result {
    private final String error;

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
