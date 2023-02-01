package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.service.CarService;
import com.haemimont.cars.core.sql.ConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;


public class CarServlet extends HttpServlet {
   CarService carService = new CarService();

   Connection connection = carService.connectToDbAndGetConnection();
    //    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        String param = request.getParameter("id");
//        Integer key = (param == null) ? null : Integer.valueOf((param.trim()));
//
//        // Check user preference for XML or JSON by inspecting
//        // the HTTP headers for the Accept key.
//        boolean json = false;
//        String accept = request.getHeader("accept");
//        if (accept != null && accept.contains("json")) json = true;
//
//        // If no query string, assume client wants the full list.
//        if (key == null) {
//            ConcurrentMap<Integer, Car> map = new ConcurrentHashMap<>();
//            Object[] list = map.values().toArray();
//            Arrays.sort(list);
//
//            String payload = novels.toXml(list);
//            if (json) payload = novels.toJson(payload); // Json preferred?
//            sendResponse(response, payload);
//        }
//        private void sendResponse(HttpServletResponse response, String payload){
//            try {
//                OutputStream out = response.getOutputStream();
//                out.write(payload.getBytes());
//                out.flush();
//            } catch (Exception e) {
//                throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
//            }
//        }
//    }
  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        Integer key = (param == null) ? null : Integer.valueOf((param.trim()));
      Car car = carService.getCar("id_car",key.toString(),connection);
        //super.doGet(req, resp);
        sendResponse(resp, car.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
        super.init();
    }

    private void sendResponse(HttpServletResponse response, String payload) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(payload.getBytes());
            out.flush();
        }
        catch(Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }
}
