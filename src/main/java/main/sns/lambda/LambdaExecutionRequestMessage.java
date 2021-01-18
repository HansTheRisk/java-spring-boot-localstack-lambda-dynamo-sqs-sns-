package main.sns.lambda;

import org.springframework.messaging.MessageHeaders;
import sns.message.InternalMessage;

public class LambdaExecutionRequestMessage extends InternalMessage {
    @Override
    public Object getPayload() {
        return null;
    }

    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
