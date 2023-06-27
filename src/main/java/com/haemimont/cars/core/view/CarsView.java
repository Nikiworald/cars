package com.haemimont.cars.core.view;

import com.haemimont.cars.core.jwttapiresult.ApiResult;
import com.haemimont.cars.core.model.Car;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


public class CarsView {
    public static void viewCar(HttpServletRequest request, HttpServletResponse response, Car car) {
        try {
            request.setAttribute("vin", car.getIdentification().getVin());
            request.setAttribute("height", car.getDimension().getHeight());
            request.setAttribute("width", car.getDimension().getWidth());
            request.setAttribute("length", car.getDimension().getLength());
            request.setAttribute("driveLine", car.getEngineInformation().getDriveLine());
            request.setAttribute("engineType", car.getEngineInformation().getEngineType());
            request.setAttribute("hybrid", car.getEngineInformation().isHybrid());
            request.setAttribute("numberOfForwardGears", car.getEngineInformation().getNumberOfForwardGears());
            request.setAttribute("transmission", car.getEngineInformation().getTransmission());
            request.setAttribute("horsepower", car.getEngineInformation().getEngineStatistics().getHorsePower());
            request.setAttribute("torque", car.getEngineInformation().getEngineStatistics().getTorque());
            request.setAttribute("cityMpg", car.getFuelInformation().getCityMpg());
            request.setAttribute("fuelType", car.getFuelInformation().getFuelType());
            request.setAttribute("highwayMpg", car.getFuelInformation().getHighwayMpg());
            request.setAttribute("classification", car.getIdentification().getClassification());
            request.setAttribute("id", car.getIdentification().getId());
            request.setAttribute("make", car.getIdentification().getMake());
            request.setAttribute("modelYear", car.getIdentification().getModelYear());
            request.setAttribute("year", car.getIdentification().getYear());
            request.setAttribute("color", car.getIdentification().getColor());
            request.setAttribute("price", car.getIdentification().getPrice());
            request.getRequestDispatcher("car.jsp").forward(request, response);
        } catch (IOException e) {
            out.println("no cars found(" + e + ")");
        } catch (Exception e) {
            out.println("no car found(" + e + ")");
        }
    }

    public static void viewCars(HttpServletRequest request, HttpServletResponse response, ArrayList<Object> cars) {
        try {
            request.setAttribute("cars", cars);
            request.getRequestDispatcher("car-browser.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("all")
    @Deprecated
    private void test(HttpServletRequest request, HttpServletResponse response, List<ApiResult> apiResultList) {     //testing java code for jsp
        apiResultList = (List<ApiResult>) request.getAttribute("apiResultList");
        if (apiResultList != null) {
            for (ApiResult result : apiResultList) {
                out.println("<tr>");
                if (result.isSuccessful()) {
                    out.println("* ");
                } else {
                    out.println("! ");
                }
                out.println(result.getName() + ":");
                out.println(result.getMessage());


            }


        }

    }
}
