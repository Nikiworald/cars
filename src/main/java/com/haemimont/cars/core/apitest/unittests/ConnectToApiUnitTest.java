package com.haemimont.cars.core.apitest.unittests;

import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapi.ConnectToApi;

import java.net.URL;
public class ConnectToApiUnitTest extends ApiUnitTest {
    public static boolean test(URL url){
     return ApiOperations.connect(url).isSuccessful();
    }
}
