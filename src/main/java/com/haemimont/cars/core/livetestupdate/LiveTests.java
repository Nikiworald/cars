package com.haemimont.cars.core.livetestupdate;

import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;

import java.util.HashMap;
import java.util.Map;

public class LiveTests {//todo optimize remove test when done or after some time is passed
    private static final Map<String, ApiIntegrationTestResult> TEST_MAP = new HashMap<>();


    public static void addLiveTest(String id, ApiIntegrationTestResult apiIntegrationTestResult) {
        if (TEST_MAP.containsKey(id)) {
            throw new IllegalArgumentException("Test with that key already exists");
        } else {
            TEST_MAP.put(id, apiIntegrationTestResult);
        }
    }

    public static ApiIntegrationTestResult getLiveTest(String id) {

        return TEST_MAP.get(id);
    }

    public static void updateLiveTest(String id, ApiIntegrationTestResult apiIntegrationTestResult) {
        TEST_MAP.replace(id, apiIntegrationTestResult);
    }


}
