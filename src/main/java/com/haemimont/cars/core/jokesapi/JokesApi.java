package com.haemimont.cars.core.jokesapi;

import com.haemimont.cars.core.logger.CustomLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JokesApi {
    private static final String siteAddress = "https://official-joke-api.appspot.com/jokes/";

    public static String getTenJokes() {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(siteAddress + "ten/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                    System.out.println(inputLine);
                }
            } catch (Exception e) {
                content.append("error:").append(e);
            } finally {
                con.disconnect();
            }
        } catch (Exception e) {
            content.append("error:");
            content.append(e);
        }


        return String.valueOf(content);
    }
    public static String getOneJoke() {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(siteAddress+"random");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                    System.out.println(inputLine);
                }
            } catch (Exception e) {
                content.append("error:").append(e);
                CustomLogger.logError(e.toString());
            } finally {
                con.disconnect();
            }
        } catch (Exception e) {
            content.append("error:");
            content.append(e);
            CustomLogger.logError(e.toString());
        }


        return String.valueOf(content);
    }
}
