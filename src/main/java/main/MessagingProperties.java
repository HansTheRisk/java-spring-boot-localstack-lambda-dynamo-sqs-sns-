package main;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("amazon")
@Data
public class MessagingProperties {
    private String requestTopic;
    private String requestQueue;
}
