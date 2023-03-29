package com.example.weatherPage.controller;

import com.example.weatherPage.model.Weather;
import com.example.weatherPage.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    @PostMapping("/weather")
    public Weather saveWeather(@RequestBody Weather weather) {

        return weatherRepository.save(weather);
    }

    @GetMapping("/weather/{weatherId}")
    public Weather getWeather(@PathVariable("weatherId") String weatherId) {

        return weatherRepository.getWeatherById(weatherId);
    }

    @DeleteMapping("/weather/{weatherId}")
    public String deleteWeather(@PathVariable("weatherId") String weatherId) {

        return weatherRepository.delete(weatherId);
    }

    @PutMapping("weather/{weatherId}")
    public String updateWeather(@PathVariable("weatherId") String weatherId, @RequestBody Weather weather) {

        return weatherRepository.update(weatherId, weather);
    }
}
