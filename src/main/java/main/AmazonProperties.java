package main;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("amazon")
@Data
public class AmazonProperties {
    private String region;
    private String accessKey;
    private String secretKey;
    private String dynamoUrl;
}
