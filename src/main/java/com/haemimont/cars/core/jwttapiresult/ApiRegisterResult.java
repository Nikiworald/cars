package com.haemimont.cars.core.jwttapiresult;

import org.apache.http.client.methods.CloseableHttpResponse;

public class ApiRegisterResult extends ApiResult{
    @Override
    public boolean isSuccessful() {
        return super.isSuccessful();
    }

    @Override
    public void setSuccessful(boolean successful) {
        super.setSuccessful(successful);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void appendMessage(String message) {
        super.appendMessage(message);
    }

    @Override
    public int getResponseCode() {
        return super.getResponseCode();
    }

    @Override
    public void setResponseCode(int responseCode) {
        super.setResponseCode(responseCode);
    }

}
