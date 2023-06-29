package com.haemimont.cars.core.apitest.callables;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiResult;

import java.util.concurrent.Callable;

public class AuthTestCallable implements Callable<ApiResult> {

    private final String URL;
    private final String JWTTOKEN;
    private final String URL_NAME_IN_CONFIG;
    private final String NAME_OF_THE_TEST;

    public AuthTestCallable(String url, String JWTToken, String urlNameInConfig, String testName) {
        this.URL = url;
        this.JWTTOKEN = JWTToken;
        this.URL_NAME_IN_CONFIG = urlNameInConfig;
        this.NAME_OF_THE_TEST = testName;
    }

    @Override
    public ApiResult call() throws Exception {
        ApiResult allTestApiResult = ApiOperations.authorizationTest(URL + Config.getPropertyByName(URL_NAME_IN_CONFIG), JWTTOKEN);//no token needed for public access
        allTestApiResult.setName(NAME_OF_THE_TEST);
        return allTestApiResult;
    }
}
