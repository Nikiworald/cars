package com.haemimont.cars.core.jwttapiresult;

public class ApiResult {
    private boolean successful;
    private String message;
    private int responseCode;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message){this.message = message;}

    public void appendMessage(String message) {
        this.message += message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
