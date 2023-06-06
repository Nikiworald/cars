package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.service.CarService;
import com.haemimont.cars.core.view.CarsView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
    final CarService<Car> crudService = new <Car>CarService<Car>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        ArrayList<Car> arrayList = crudService.get("id", id);
        CarsView.viewCar(req, resp, arrayList.get(0));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Car car = null;
        try {
            String height = req.getParameter("height");
            String width = req.getParameter("width");
            String length = req.getParameter("length");
            String driveLine = req.getParameter("driveLine");
            String engineType = req.getParameter("engineType");
            String hybrid = req.getParameter("hybrid");
            String numberOfForwardGears = req.getParameter("numberOfForwardGears");
            String transmission = req.getParameter("transmission");
            String horsepower = req.getParameter("horsepower");
            String torque = req.getParameter("torque");
            String cityMpg = req.getParameter("cityMpg");
            String fuelType = req.getParameter("fuelType");
            String highwayMpg = req.getParameter("highwayMpg");
            String classification = req.getParameter("classification");
            String id = req.getParameter("id");
            String make = req.getParameter("make");
            String modelYear = req.getParameter("modelYear");
            String year = req.getParameter("year");
            String color = req.getParameter("color");
            String price = req.getParameter("price");
            EngineInformation engineInformation = new EngineInformation();
            Dimension dimension = new Dimension();
            Identification identification = new Identification();
            FuelInformation fuelInformation = new FuelInformation();
            engineInformation.setEngineStatistics(new EngineStatistics());
            if (hybrid.equals("on")) {
                hybrid = "true";
            } else {
                hybrid = "false";
            }
            car = CarBuilder.newInstance().setFuelInformation(fuelInformation)
                    .setEngineInformation(engineInformation).setDimension(dimension).setIdentification(identification)
                    .setHeight(height).setWidth(width).setLength(length)
                    .setDriveLine(driveLine).setEngineType(engineType).setEngineType(engineType).setHybrid(hybrid)
                    .setNumberOfForwardGears(numberOfForwardGears).setTransmission(transmission).setHorsePower(horsepower)
                    .setTorque(torque).setCityMpg(cityMpg).setFuelType(fuelType).setHighwayMpg(highwayMpg)
                    .setClassification(classification).setId(id).setMake(make).setModelYear(modelYear).setYear(year)
                    .setColor(color).setPrice(Double.parseDouble(price))
                    .build();
        } catch (NullPointerException nullPointerException) {
            sendResponse(resp, "Null values");
        } catch (NumberFormatException numberFormatException) {
            sendResponse(resp, "Numbers were not entered correctly");
        } catch (Exception e) {
            sendResponse(resp, "something went wrong");
        }

        String response = crudService.put(car);//after we build the car we upload it to the db and get back a response
        sendResponse(resp, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void init() {
//        InitDB.initializeDb();
    }


    private void sendResponse(HttpServletResponse response, String payload) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(payload.getBytes());
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }
}
