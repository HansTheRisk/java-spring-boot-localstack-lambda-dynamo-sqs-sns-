package main.sns.lambda;

import main.MessagingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sns.service.internal.InternalNotificationMessagingService;

@Component
public class LambdaExecutionRequestMessageService extends InternalNotificationMessagingService {

    @Autowired
    private MessagingProperties properties;

    public void publish(LambdaExecutionRequestMessage message) {
        super.publish(properties.getRequestTopic(), message);
    }
}
