package com.example.weatherPage.config;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {

    public DynamoDBMapper dynamoDBMapper() {

        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {

        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "dynamodb.eu-north-1.amazonaws.com",
                                "eu-central-1"
                        )
                )
                .withCredentials(

                        new StaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        "AKIAW647GCM5YML5GUOG",
                                        "fqLI5oH/LvhD0SKozDreEdbYel+v8oGoEIxpjobL"
                                )
                        )
                ).build();
    }

}
