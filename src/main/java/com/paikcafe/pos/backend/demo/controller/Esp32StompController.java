package com.paikcafe.pos.backend.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import com.paikcafe.pos.backend.demo.websocket.Esp32WebSocketHandler;

@Controller
public class Esp32StompController {
    private final Esp32WebSocketHandler esp32WebSocketHandler;

    public Esp32StompController(Esp32WebSocketHandler esp32WebSocketHandler) {
        this.esp32WebSocketHandler = esp32WebSocketHandler;
    }

    @MessageMapping("/esp32")
    public void relayToEsp32(String message) {
        // Optionally log
        System.out.println("Received from React Native: " + message);
        // Relay to ESP32
        esp32WebSocketHandler.sendToAll(message);
    }
}
