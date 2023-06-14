package com.haemimont.cars.core.tools;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class FromJsonToJokes {
    public static String convert(String json){
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        return ((jsonObject.get("setup")) + "-"+
                jsonObject.get("punchline")).replaceAll("\"","");
    }
}
