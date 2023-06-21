package com.haemimont.cars.core.jwttapiresult;

import java.util.List;

public class ApiIntegrationTestResult extends ApiResult{
    List<ApiResult> apiResultList;
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
    public void setMessage(String message) {
        super.setMessage(message);
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
    public List<ApiResult> getApiResults(){
        return apiResultList;
    }
    public void addApiResultToList(ApiResult apiResult){
        apiResultList.add(apiResult);
    }
}
