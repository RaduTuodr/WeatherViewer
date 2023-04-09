package com.example.weatherPage.converter;

import com.example.weatherPage.model.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WeatherConverter {

    public Weather WeatherFromJsonArray(JSONObject weatherData) {

        JSONArray weatherDetailsArray = (JSONArray) weatherData.get("weather");
        JSONObject weatherDetails = (JSONObject) weatherDetailsArray.get(0);

        return new Weather(

                String.valueOf(weatherData.get("id")),
                (String) weatherDetails.get("main"),
                (String) weatherDetails.get("description")
        );
    }
}
