package com.haemimont.cars.core.tools;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@SuppressWarnings("unused")//they are used in jsp
public class FromJsonToJokes {
    // --Commented out by Inspection START (6/22/2023 2:58 PM):
    public static String convert(String json) {
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        return ((jsonObject.get("setup")) + "-" +
                jsonObject.get("punchline")).replaceAll("\"", "");
    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
}
