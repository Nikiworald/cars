package com.haemimont.cars.core.jwttapi;

import com.haemimont.cars.core.jwttresult.ApiConnectionResult;
import org.junit.Test;
import org.junit.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class ConnectToApiTest {
    @Test
    public void testConnectionToApi() {
        URL url = null;
        try {
            url = new URL("http://192.168.250.206:8080/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ApiConnectionResult apiConnectionResult = ConnectToApi.connect(url);
        Assert.assertTrue(apiConnectionResult.isSuccessful());
    }
}