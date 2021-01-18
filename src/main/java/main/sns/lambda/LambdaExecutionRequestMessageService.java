package main.sns.lambda;

import main.MessagingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sns.message.InternalMessage;
import sns.service.internal.InternalNotificationMessagingService;

@Component
public class LambdaExecutionRequestMessageService extends InternalNotificationMessagingService<InternalMessage<String>> {

    @Autowired
    private MessagingProperties properties;

    public void publish(InternalMessage<String> message) {
        super.publish(properties.getRequestTopic(), message);
    }
}
