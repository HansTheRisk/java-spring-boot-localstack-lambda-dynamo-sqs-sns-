package main;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.github.dynamobee.Dynamobee;
import config.AmazonDynamoConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig extends AmazonDynamoConfig {

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
