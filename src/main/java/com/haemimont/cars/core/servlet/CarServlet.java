package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.service.CarService;
import com.haemimont.cars.core.service.CrudService;
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
    CrudService crudService = new CarService();
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
       ArrayList arrayList =  crudService.get("id",id);
       CarsView.viewCar(req,resp,(Car) arrayList.get(0));

//        String filter = req.getParameter("filter");
//        String value = req.getParameter("value");
//        String maxValue = req.getParameter("maxvalue");
//        String minValue = req.getParameter("minvalue");
//        ArrayList<Object> arrayList = null;
//        if (filter != null && !filter.equals("")) {//if filter/criteria is not null then get cars by that filter/criteria and its value/s
//
//            if (filter.equals("price")) {
//                if (maxValue == null || minValue == null) {
//                    sendResponse(resp, "car/s not found");
//                }
//                String combined = minValue.trim() + "--" + maxValue.trim();
//                arrayList = crudService.get(filter, combined);
//            } else {
//                arrayList = crudService.get(filter, value);
//            }
//
//        } else {//else we get all the cars
//            arrayList = crudService.get("all", "");
//        }
//        if (arrayList.isEmpty() || arrayList == null) {
//            sendResponse(resp, "car/s not found");
//        } else {
//            Gson gson = new Gson();
////            sendResponse(resp, gson.toJson(arrayList));
//            CarView.viewCar(req,resp,arrayList);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        if(hybrid.equals("on")){hybrid = "true";}else {hybrid = "false";}
        car = CarBuilder.newInstance().setFuelInformation(fuelInformation)
                .setEngineInformation(engineInformation).setDimension(dimension).setIdentification(identification)
                .setHeight(height).setWidth(width).setLength(length)
                .setDriveLine(driveLine).setEngineType(engineType).setEngineType(engineType).setHybrid(hybrid)
                .setNumberOfForwardGears(numberOfForwardGears).setTransmission(transmission).setHorsePower(horsepower)
                .setTorque(torque).setCityMpg(cityMpg).setFuelType(fuelType).setHighwayMpg(highwayMpg)
                .setClassification(classification).setId(id).setMake(make).setModelYear(modelYear).setYear(year)
                .setColor(color).setPrice(Double.valueOf(price))
                .build();
        }catch (NullPointerException nullPointerException){sendResponse(resp,"Null values");}
        catch (NumberFormatException numberFormatException){sendResponse(resp,"Numbers were not entered correctly");}
        catch (Exception e ){sendResponse(resp,"something went wrong");}

        String response = crudService.put(car);
        sendResponse(resp,response);
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
    public void init() throws ServletException {
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
