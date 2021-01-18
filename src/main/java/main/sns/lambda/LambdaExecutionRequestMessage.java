package main.sns.lambda;

import org.springframework.messaging.MessageHeaders;
import sns.message.InternalMessage;

public class LambdaExecutionRequestMessage extends InternalMessage<String> {

    public LambdaExecutionRequestMessage(String payload) {
        super(payload);
    }

    @Override
    public String getPayload() {
        return payload;
    }

    @Override
    public MessageHeaders getHeaders() {
        return messageHeaders;
    }
}
