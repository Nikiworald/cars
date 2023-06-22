package com.haemimont.cars.core.jwttapiresult;

public class ApiResult {
    private boolean successful;
    private String message;


    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void appendMessage(String message) {
        if (this.message == null) {
            this.message = message;
        } else {
            this.message += message;
        }
    }
}
