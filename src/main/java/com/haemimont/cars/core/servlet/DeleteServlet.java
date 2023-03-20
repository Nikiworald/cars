package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.service.CarService;
import com.haemimont.cars.core.service.CrudService;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.tools.DbUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    CrudService crudService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CarStatements carStatements = new CarStatements();
        String vin = req.getParameter("vin");
        if (vin != null&&vin.equals("")) {
            sendResponse(resp, "no vin");
        }
        int id = carStatements.getIdByVin(vin, DbUtil.getConnection());
       boolean check =crudService.delete(id);
       if(check){sendResponse(resp, "successfully deleted the car");}
       else {sendResponse(resp, "could not delete the car");}
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

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
