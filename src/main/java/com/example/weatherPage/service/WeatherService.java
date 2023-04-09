package com.example.weatherPage.service;

import com.example.weatherPage.converter.WeatherConverter;
import com.example.weatherPage.model.Weather;
import com.example.weatherPage.repository.WeatherRepository;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class WeatherService {

    private static final String URI = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String API_ID = "b6e61cf7263d8ff4e7dbeb4cbe84bfb7";

    @Autowired
    private WeatherRepository weatherRepository;

    private final WeatherConverter weatherConverter = new WeatherConverter();

    public static @NotNull JSONObject getWeatherJson(String cityName) {

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + API_ID);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {

                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder infoString = new StringBuilder();
                Scanner urlScanner = new Scanner(url.openStream());
                while (urlScanner.hasNext()) {
                    infoString.append(urlScanner.nextLine());
                }
                urlScanner.close();

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(String.valueOf(infoString));
                JSONArray dataObject = new JSONArray();
                dataObject.add(obj);

                JSONObject weatherData = (JSONObject) dataObject.get(0);
                return weatherData;
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Weather getWeatherByCityName(String cityName) {

        return weatherConverter.WeatherFromJsonArray(getWeatherJson(cityName));
    }

    public void save(Weather weather) {

        weatherRepository.save(weather);
    }
}
