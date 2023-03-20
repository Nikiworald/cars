package com.haemimont.cars.core.view;

import com.haemimont.cars.core.model.Car;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

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
        } catch (ServletException e) {
            out.println("no car found(" + e + ")");
        } catch (IOException e) {
            out.println("no cars found(" + e + ")");
        } catch (Exception e) {
            out.println("no car found(" + e + ")");
        }
    }

    public static void viewCars(HttpServletRequest request, HttpServletResponse response, ArrayList<Object> cars) {
        try {
            request.setAttribute("cars", cars);
            request.getRequestDispatcher("carbrowser.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void test(HttpServletRequest request, HttpServletResponse response, ArrayList<Object> cars) {     //testing javacode for jsp
        try {
            for (Object object : cars) {
                Car car = (Car) object;
                out.println("<td>" + car.getIdentification().getVin() + "</td>");
                out.println("<td>" + car.getIdentification().getMake() + "</td>");
                out.println("<td>" + car.getIdentification().getModelYear() + "</td>");
                out.println("<td>" + car.getIdentification().getClassification() + "</td>");
                out.println("<td>" + car.getFuelInformation().getFuelType() + "</td>");
                out.println("<td>" + car.getEngineInformation().getTransmission() + "</td>");
                out.println("<td>" + car.getEngineInformation().getDriveLine() + "</td>");
                out.println("<td>" + car.getEngineInformation().getEngineType() + "</td>");
                out.println("<td>" + car.getIdentification().getPrice() + "</td>");
                out.println("<td>");
                out.println("<form action=" + "VinServlet" + " method=" + "get" + ">" +
                        "<input type=" + "hidden" + " name=" + "vin" + " value=" + car.getIdentification().getVin() + " />" +
                        "<input type=" + "submit" + " value=view>" +
                        "</form>");
                out.println("</td>");
            }
        } catch (Exception e) {
            out.println("no cars found(" + e + ")");
        }
    }
}
