package com.haemimont.cars.core.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.haemimont.cars.core.jokesapi.JokesApi;
import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.Joke;
import com.haemimont.cars.core.model.JokeBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@WebServlet("/jokeServlet")
public class JokeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Joke> jokes = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        String jsonArrString = JokesApi.getTenJokes();
//        JsonObject jsonObject =(JsonObject) new JsonParser().parse(jsonJokes);
        try {
            JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonArrString);
            for (Object json : jsonArray) {
                JsonObject jsonJoke = (JsonObject) json;
                Joke joke = JokeBuilder.newInstance().setType(String.valueOf(jsonJoke.get("type")))
                        .setSetUp(String.valueOf(jsonJoke.get("setup"))).setPunchline(String
                                .valueOf(jsonJoke.get("punchline"))).setId(Integer.parseInt(String.valueOf(jsonJoke.get("id")))).build();
                jokes.add(joke);
            }
            req.setAttribute("jokes", jokes);
            req.getRequestDispatcher("jokes.jsp").forward(req, resp);
        } catch (Exception e) {
            sendResponse(resp, "something went wrong");
            CustomLogger.logError(jsonArrString);
        }
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
