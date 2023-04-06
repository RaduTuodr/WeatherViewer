package com.example.weatherPage.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.weatherPage.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Weather getWeatherById(String weatherId) {

        return dynamoDBMapper.load(Weather.class, weatherId);
    }

    public Weather save(Weather weather) {

        dynamoDBMapper.save(weather);
        return weather;
    }

    public String update(String weatherId, Weather weather) {

        dynamoDBMapper.save(weather,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("weatherId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(weatherId)
                                ))
                );

        return weatherId;
    }

    public String delete(String weatherId) {

        Weather weather = dynamoDBMapper.load(Weather.class, weatherId);
        dynamoDBMapper.delete(weather);

        return "Weather deleted!";
    }
}
