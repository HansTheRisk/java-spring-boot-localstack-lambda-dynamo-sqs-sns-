package main;

import config.AmazonMessagingConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DynamoConfig.class, AmazonMessagingConfig.class})
@EnableAutoConfiguration
@EnableConfigurationProperties(MessagingProperties.class)
public class AppConfig {
}
