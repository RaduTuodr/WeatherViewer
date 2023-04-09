package com.example.weatherPage;

import com.example.weatherPage.service.WeatherService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherPageApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeatherPageApplication.class, args);

		JSONObject jsonObject = WeatherService.getWeatherJson("London");
		System.out.println(jsonObject);
	}
}
