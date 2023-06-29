package com.haemimont.cars.core.apitest.callables;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import org.json.JSONObject;

import java.util.concurrent.Callable;

public class LogInCallable implements Callable<ApiResult> {
    private final String url;
    private final JSONObject jsonObject;


    public LogInCallable(String url, JSONObject jsonObject) {
        this.url = url;
        this.jsonObject = jsonObject;
    }

    @Override
    public ApiResult call() throws Exception {
        synchronized (jsonObject) {
            ApiResult apiRegisterResult = ApiOperations.register(url + Config.getPropertyByName("loginUrl"), jsonObject);
            apiRegisterResult.setName("login test");
            return apiRegisterResult;
        }
    }
}
