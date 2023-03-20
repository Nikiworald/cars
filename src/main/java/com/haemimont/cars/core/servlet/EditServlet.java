package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.service.CarService;
import com.haemimont.cars.core.service.CrudService;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.tools.DbUtil;
import com.haemimont.cars.core.view.CarsView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/editServlet")
public class EditServlet extends HttpServlet {
    //    CrudService crudService = new CarService();
    CrudService crudService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vin = req.getParameter("vin");
        if ( vin != null&&vin.equals("")) {
            sendResponse(resp, "no vin");
        } else {
            CarStatements carStatements = new CarStatements();
            int carId = carStatements.getIdByVin(vin, DbUtil.getConnection());
            Car car = carStatements.getCarById(carId, DbUtil.getConnection());
            if (car == null) {
                sendResponse(resp, "no car with matching vin");
            } else {
                req.setAttribute("vin", car.getIdentification().getVin());
                req.setAttribute("height", car.getDimension().getHeight());
                req.setAttribute("width", car.getDimension().getWidth());
                req.setAttribute("length", car.getDimension().getLength());
                req.setAttribute("driveLine", car.getEngineInformation().getDriveLine());
                req.setAttribute("engineType", car.getEngineInformation().getEngineType());
                req.setAttribute("hybrid", car.getEngineInformation().isHybrid());
//                req.setAttribute("hybrid", true);
                if(car.getEngineInformation().isHybrid()){req.setAttribute("hybridCheckBox","checked");}
                else {req.setAttribute("hybridCheckBox","unchecked");}
                req.setAttribute("numberOfForwardGears", car.getEngineInformation().getNumberOfForwardGears());
                req.setAttribute("transmission", car.getEngineInformation().getTransmission());
                req.setAttribute("horsepower", car.getEngineInformation().getEngineStatistics().getHorsePower());
                req.setAttribute("torque", car.getEngineInformation().getEngineStatistics().getTorque());
                req.setAttribute("cityMpg", car.getFuelInformation().getCityMpg());
                req.setAttribute("fuelType", car.getFuelInformation().getFuelType());
                req.setAttribute("highwayMpg", car.getFuelInformation().getHighwayMpg());
                req.setAttribute("classification", car.getIdentification().getClassification());
                req.setAttribute("id", car.getIdentification().getId());
                req.setAttribute("make", car.getIdentification().getMake());
                req.setAttribute("modelYear", car.getIdentification().getModelYear());
                req.setAttribute("year", car.getIdentification().getYear());
                req.setAttribute("color", car.getIdentification().getColor());
                req.setAttribute("price", car.getIdentification().getPrice());
                req.getRequestDispatcher("updatecar.jsp").forward(req, resp);

            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Car car = null;
        try {       //get all the parameters and try to build car
            String height = req.getParameter("height");
            String width = req.getParameter("width");
            String length = req.getParameter("length");
            String driveLine = req.getParameter("driveLine");
            String engineType = req.getParameter("engineType");
            String stringHybrid = req.getParameter("hybrid");
            boolean hybrid = false;
            if(stringHybrid!=null&&stringHybrid.equals("on")){hybrid = true;}
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
            String vin = req.getParameter("vin");       //not sure what to choose
            if (vin == null||vin.equals("")) {
                req.getAttribute("vin");
            }
            EngineInformation engineInformation = new EngineInformation();
            Dimension dimension = new Dimension();
            Identification identification = new Identification();
            FuelInformation fuelInformation = new FuelInformation();
            engineInformation.setEngineStatistics(new EngineStatistics());
            car = CarBuilder.newInstance().setFuelInformation(fuelInformation)
                    .setEngineInformation(engineInformation).setDimension(dimension).setIdentification(identification)
                    .setHeight(height).setWidth(width).setLength(length)
                    .setDriveLine(driveLine).setEngineType(engineType).setEngineType(engineType).setHybrid(hybrid)
                    .setNumberOfForwardGears(numberOfForwardGears).setTransmission(transmission).setHorsePower(horsepower)
                    .setTorque(torque).setCityMpg(cityMpg).setFuelType(fuelType).setHighwayMpg(highwayMpg)
                    .setClassification(classification).setId(id).setMake(make).setModelYear(modelYear).setYear(year)
                    .setColor(color).setPrice(Double.parseDouble(price))
                    .build();
            car.getIdentification().setVin(vin);
        } catch (NullPointerException nullPointerException) {
            sendResponse(resp, "Null values");
        } catch (NumberFormatException numberFormatException) {
            sendResponse(resp, "Numbers were not entered correctly");
        } catch (Exception e) {
            sendResponse(resp, "something went wrong");
        }


        Car updateCar = (com.haemimont.cars.core.model.Car) crudService.update(car);        //we update the car and get back the updated car
        CarsView.viewCar(req, resp, updateCar);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
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
