package com.example.fooddelivery_lt152011.LoginScreen;

public class ModelStatusUser {
    private boolean error;
    private String message;

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModelStatusUser() {
    }

    public ModelStatusUser(boolean error, String message) {
        this.error = error;
        this.message = message;
    }
}
