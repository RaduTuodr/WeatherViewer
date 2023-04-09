package com.example.weatherPage.controller;

import com.example.weatherPage.converter.WeatherConverter;
import com.example.weatherPage.model.Weather;
import com.example.weatherPage.repository.WeatherRepository;
import com.example.weatherPage.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{cityName}")
    public Weather getWeather(@PathVariable("cityName") String cityName) {

        Weather weather = weatherService.getWeatherByCityName(cityName);
        weatherService.save(weather);
        return weather;
    }
}
