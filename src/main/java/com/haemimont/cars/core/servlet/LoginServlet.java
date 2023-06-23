package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        boolean loggedIn = userService.get(name, password);
        if (loggedIn) {
            HttpSession session = req.getSession();
            session.setAttribute("user", name);
            //setting session to expiry in 30 min s
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", name);
            resp.addCookie(userName);
            //Get the encoded URL string
            String encodedURL = resp.encodeRedirectURL("home.jsp");
            resp.sendRedirect(encodedURL);
        } else {
            sendResponse(resp, "wrong user name or password");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @SuppressWarnings("SameParameterValue")
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
