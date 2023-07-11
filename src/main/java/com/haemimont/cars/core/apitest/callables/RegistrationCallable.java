package com.haemimont.cars.core.apitest.callables;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import com.haemimont.cars.core.livetestupdate.LiveTests;
import org.json.JSONObject;

import java.util.concurrent.Callable;

public class RegistrationCallable implements Callable<ApiResult> {
    private final String URL;
    private final JSONObject JSONOBJECT;
    private final ApiIntegrationTestResult API_INTEGRATION_TEST_RESULT;
    private final String ID;


    public RegistrationCallable(String integrationTestId, ApiIntegrationTestResult apiIntegrationTestResult,
                                String url, JSONObject jsonObject) {
        this.URL = url;
        this.JSONOBJECT = jsonObject;
        this.API_INTEGRATION_TEST_RESULT = apiIntegrationTestResult;
        this.ID = integrationTestId;
    }


    @Override
    public ApiResult call() {
        synchronized (JSONOBJECT) {
            ApiResult apiRegisterResult = ApiOperations.register(URL + Config.getPropertyByName("registerUrl"),
                    JSONOBJECT);
            apiRegisterResult.setName("registration test");
            synchronized (API_INTEGRATION_TEST_RESULT) {
                API_INTEGRATION_TEST_RESULT.replaceApiResultById(1, apiRegisterResult);
                LiveTests.updateLiveTest(ID, API_INTEGRATION_TEST_RESULT);
            }
            return apiRegisterResult;
        }
    }
}
