package com.haemimont.cars.core.apitest.callables;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import com.haemimont.cars.core.livetestupdate.LiveTests;

import java.util.concurrent.Callable;

public class AuthTestCallable implements Callable<ApiResult> {

    private final int unitTestId;
    private final String URL;
    private final String JWT_TOKEN;
    private final String URL_NAME_IN_CONFIG;
    private final String NAME_OF_THE_TEST;
    private final ApiIntegrationTestResult API_INTEGRATION_TEST_RESULT;

    private final String ID;

    public AuthTestCallable(String integrationTestId, int unitTestId, ApiIntegrationTestResult apiIntegrationTestResult, String url, String JWTToken, String urlNameInConfig, String testName) {
        this.unitTestId = unitTestId;
        this.URL = url;
        this.JWT_TOKEN = JWTToken;
        this.URL_NAME_IN_CONFIG = urlNameInConfig;
        this.NAME_OF_THE_TEST = testName;
        this.API_INTEGRATION_TEST_RESULT = apiIntegrationTestResult;
        this.ID = integrationTestId;
    }

    @Override
    public ApiResult call() {
        try {
            // Adding a 3-second delay (3000 milliseconds)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }
        ApiResult ApiResult = ApiOperations.authorizationTest(URL + Config.getPropertyByName(URL_NAME_IN_CONFIG), JWT_TOKEN);//no token needed for public access
        ApiResult.setName(NAME_OF_THE_TEST);
        synchronized (API_INTEGRATION_TEST_RESULT) {
            API_INTEGRATION_TEST_RESULT.replaceApiResultById(unitTestId, ApiResult);

            LiveTests.updateLiveTest(ID, API_INTEGRATION_TEST_RESULT);

        }
        return ApiResult;
    }
}
