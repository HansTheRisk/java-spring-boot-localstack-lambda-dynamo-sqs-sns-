package main.sqs.lambda;

import main.MessagingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sns.message.InternalMessage;
import sqs.service.internal.InternalQueueMessagingService;

@Component
public class LambdaExecutionRequestSqsService extends InternalQueueMessagingService {

    @Autowired
    private MessagingProperties properties;

    public InternalMessage<String> receive() {
        return super.receive(properties.getRequestQueue());
    }

}
