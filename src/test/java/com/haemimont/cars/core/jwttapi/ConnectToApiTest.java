package com.haemimont.cars.core.jwttapi;

import com.haemimont.cars.core.jwttapiresult.ApiConnectionResult;

import org.junit.Test;
import org.junit.Assert;

import java.net.URL;

public class ConnectToApiTest {
    URL url;
    public ConnectToApiTest(URL url){
        this.url = url;
    }
    @Test
    public void testConnectionToApi() {
        ApiConnectionResult apiConnectionResult = ConnectToApi.connect(url);
        Assert.assertTrue(apiConnectionResult.isSuccessful());
    }
}