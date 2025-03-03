package com.cloud.CloudProd.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class AwsConfig {
    private final Dotenv dotenv = Dotenv.load();

    @Value("${AWS_ACCESS_KEY:#{null}}")
    private String accessKey;

    @Value("${AWS_SECRET_KEY:#{null}}")
    private String secretKey;

    @Value("${AWS_REGION:us-east-1}")
    private String region;

    @Value("${AWS_DYNAMODB_TABLE:ShopeeProducts}")
    private String dynamoDbTable;


        @Bean
        public DynamoDbClient dynamoDbClient() {
            return DynamoDbClient.builder()
                    .region(Region.of(region)) // Thay bằng region của bạn, ví dụ: US_EAST_1
                    .credentialsProvider(DefaultCredentialsProvider.create())
                    .build();
        }
}
