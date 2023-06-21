package com.haemimont.cars.core.apitest.unittests;

import com.haemimont.cars.core.jwttapi.RegisterToApi;
import org.json.JSONObject;

import java.net.URL;

@Deprecated//doesn't change anything and returns less information that needed
public class RegisterToApiUnitTest extends ApiUnitTest {
    public static boolean test(URL url, JSONObject jsonObject){
       return RegisterToApi.register(url,jsonObject).isSuccessful();
    }
}
