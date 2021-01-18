package main.controller;

import main.sns.lambda.LambdaExecutionRequestMessageService;
import main.sqs.lambda.LambdaExecutionRequestSqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sns.message.InternalMessage;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LambdaExecutionRequestMessageService requestMessageService;

    @Autowired
    private LambdaExecutionRequestSqsService requestSqsService;

    @GetMapping("/ping")
    public ResponseEntity ping() {
        requestMessageService.publish(new InternalMessage<>("pong"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pong")
    public ResponseEntity pong() {
        return ResponseEntity.ok(requestSqsService.receive().getPayload());
    }

}
