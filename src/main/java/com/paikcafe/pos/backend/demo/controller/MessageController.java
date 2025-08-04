package com.paikcafe.pos.backend.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/hello") // Client sends to /app/hello
    @SendTo("/topic/greetings") // Broadcast to /topic/greetings
    public String greeting(String message) {
        return "Hello, " + message + "!";
    }
}
