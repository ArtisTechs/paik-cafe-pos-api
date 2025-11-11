package com.paikcafe.pos.backend.demo.config;

import com.paikcafe.pos.backend.demo.websocket.RawWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class RawWebSocketConfig implements WebSocketConfigurer {

    private final RawWebSocketHandler rawWebSocketHandler;

    public RawWebSocketConfig(RawWebSocketHandler rawWebSocketHandler) {
        this.rawWebSocketHandler = rawWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(rawWebSocketHandler, "/ws")
                // use one of these; both shown for reference
                .setAllowedOrigins("*");           // dev only
                // .setAllowedOriginPatterns("*"); // Spring 6+ wildcard support
    }

    /** Tomcat WS container tuning to avoid “session closed” sends and long blocks. */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean c = new ServletServerContainerFactoryBean();
        c.setMaxTextMessageBufferSize(512 * 1024);
        c.setMaxBinaryMessageBufferSize(512 * 1024);
        c.setMaxSessionIdleTimeout(60_000L); // 60s idle → close
        // Optional: limit synchronous send blocking time (ms)
        c.setAsyncSendTimeout(15_000L);
        return c;
    }
}
