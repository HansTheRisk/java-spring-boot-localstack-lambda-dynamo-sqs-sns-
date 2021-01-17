package main;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.github.dynamobee.Dynamobee;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(AmazonProperties.class)
@EnableDynamoDBRepositories
public class AppConfig {

    @Autowired
    private AmazonProperties amazonProperties;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
        AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonProperties.getAccessKey(),
                                                                                                         amazonProperties.getSecretKey()));
        builder.setCredentials(provider);
        builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonProperties.getDynamoUrl(),
                                                                                    amazonProperties.getRegion()));
        return builder.build();
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonProperties.getAccessKey(), amazonProperties.getSecretKey());
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }

    @Bean
    public DynamoDB dynamoDB() {
        return new DynamoDB(amazonDynamoDB());
    }

    @Bean
    public Dynamobee dynamobee(){
        Dynamobee runner = new Dynamobee(amazonDynamoDB());
        runner.setChangeLogsScanPackage(
                "main.changelogs"); // the package to be scanned for changesets

        return runner;
    }
}
