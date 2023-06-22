package com.haemimont.cars.core.jwttapiresult;

import java.util.ArrayList;
import java.util.List;

public class ApiIntegrationTestResult extends ApiResult{
   final List<ApiResult> API_RESULT_LIST = new ArrayList<>();
    public List<ApiResult> getApiResults(){
        return API_RESULT_LIST;
    }
    public void addApiResultToList(ApiResult apiResult){
        API_RESULT_LIST.add(apiResult);
    }
}
