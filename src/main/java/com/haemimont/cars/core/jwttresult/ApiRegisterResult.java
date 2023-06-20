package com.haemimont.cars.core.jwttresult;

import org.apache.http.client.methods.CloseableHttpResponse;

public class ApiRegisterResult extends ApiResult{
    private CloseableHttpResponse closeableHttpResponse;
    @Override
    public boolean isSuccessful() {
        return super.isSuccessful();
    }

    @Override
    public void setSuccessful(boolean successful) {
        super.setSuccessful(successful);
    }

    public CloseableHttpResponse getCloseableHttpResponse() {
        return closeableHttpResponse;
    }

    public void setCloseableHttpResponse(CloseableHttpResponse closeableHttpResponse) {
        this.closeableHttpResponse = closeableHttpResponse;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
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
