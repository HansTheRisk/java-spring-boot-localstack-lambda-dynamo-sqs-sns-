package main.controller;

import main.sns.lambda.LambdaExecutionRequestMessage;
import main.sns.lambda.LambdaExecutionRequestMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LambdaExecutionRequestMessageService requestMessageService;

    @GetMapping("/echo")
    public ResponseEntity echo() {
        requestMessageService.publish(new LambdaExecutionRequestMessage("Echo"));
        return ResponseEntity.ok().build();
    }

}
