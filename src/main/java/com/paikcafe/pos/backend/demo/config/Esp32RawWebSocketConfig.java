package com.paikcafe.pos.backend.demo.config;

import com.paikcafe.pos.backend.demo.websocket.Esp32WebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class Esp32RawWebSocketConfig implements WebSocketConfigurer {
    private final Esp32WebSocketHandler handler;

    public Esp32RawWebSocketConfig(Esp32WebSocketHandler handler) {
        this.handler = handler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/esp32ws").setAllowedOrigins("*");
    }
}
