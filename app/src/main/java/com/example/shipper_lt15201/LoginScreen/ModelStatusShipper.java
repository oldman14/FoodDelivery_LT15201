package com.example.shipper_lt15201.LoginScreen;

public class ModelStatusShipper {
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

    public ModelStatusShipper() {
    }

    public ModelStatusShipper(boolean error, String message) {
        this.error = error;
        this.message = message;
    }
}
