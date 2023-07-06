package com.haemimont.cars.core.jwttapiresult;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ApiIntegrationTestResult extends ApiResult {
    private final CopyOnWriteArrayList<ApiResult> API_RESULT_LIST = new CopyOnWriteArrayList<>();
//  private  final List<ApiResult> API_RESULT_LIST = new ArrayList<>();

    public static ApiIntegrationTestResult generateDefaultResult() {
        ApiIntegrationTestResult apiIntegrationTestResult = new ApiIntegrationTestResult();
        ApiResult register = new ApiResult();
        register.setName("registrationTest");
        register.setId(1);
        register.setMessage("testing..");
        ApiResult login = new ApiResult();
        login.setName("loginTest");
        login.setId(2);
        login.setMessage("testing..");
        ApiResult publicTest = new ApiResult();
        publicTest.setName("publicTest");
        publicTest.setId(3);
        publicTest.setMessage("testing..");
        ApiResult userTest = new ApiResult();
        userTest.setName("userTest");
        userTest.setId(4);
        userTest.setMessage("testing..");
        ApiResult modTest = new ApiResult();
        modTest.setName("modTest");
        modTest.setId(5);
        modTest.setMessage("testing..");
        ApiResult adminTest = new ApiResult();
        adminTest.setName("adminTest");
        adminTest.setId(6);
        adminTest.setMessage("testing..");
        apiIntegrationTestResult.addApiResultToList(register);
        apiIntegrationTestResult.addApiResultToList(login);
        apiIntegrationTestResult.addApiResultToList(publicTest);
        apiIntegrationTestResult.addApiResultToList(userTest);
        apiIntegrationTestResult.addApiResultToList(modTest);
        apiIntegrationTestResult.addApiResultToList(adminTest);

        return apiIntegrationTestResult;
    }

    public List<ApiResult> getApiResults() {
        return API_RESULT_LIST;
    }

    public void addApiResultToList(ApiResult apiResult) {
        API_RESULT_LIST.add(apiResult);
    }

    public void replaceApiResultById(int id, ApiResult apiResult) {
        boolean found = false;
        for (ApiResult a : API_RESULT_LIST
        ) {
            if (a.getId() == id) {
                API_RESULT_LIST.remove(a);
                apiResult.setId(id);//not sure about this but I am going to leave it for now
                API_RESULT_LIST.add(apiResult);
                found = true;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("No result with that id(" + id + ")found");
        }


    }
}
