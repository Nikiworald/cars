package com.haemimont.cars.core.jwttapiresult;

public class ApiResult {
    private int id;//a way to replace the default result(loading..) with the actual result when the test is done
    //and the order that the results are going to be displayed on the page 1 register 2 login ...
    private boolean successful;
    private String message;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        if (getMessage() == null) {
            setMessage(message);
        } else {
            this.message += message;
        }
    }
}
