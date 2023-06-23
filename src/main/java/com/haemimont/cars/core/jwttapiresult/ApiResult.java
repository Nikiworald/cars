package com.haemimont.cars.core.jwttapiresult;

public class ApiResult {
    private boolean successful;
    private String message;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void appendMessage(String message) {
        if (getMessage()== null) {
            setMessage(message);
        } else {
            this.message += message;
        }
    }
}
