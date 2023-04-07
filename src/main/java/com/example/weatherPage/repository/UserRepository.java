package com.example.weatherPage.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.weatherPage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User getUserById(String userId) {

        return dynamoDBMapper.load(User.class, userId);
    }

    public User save(User user) {

        dynamoDBMapper.save(user);
        return user;
    }

    public String update(String userId, User user) {

        dynamoDBMapper.save(user,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("userId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(userId)
                                )));

        return userId;
    }

    public String delete(String userId) {

        User user = dynamoDBMapper.load(User.class, userId);
        dynamoDBMapper.delete(user);

        return "User deleted!";
    }
}
