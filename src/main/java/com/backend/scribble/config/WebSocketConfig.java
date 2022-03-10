package com.backend.scribble.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${app.allowed_url}")
    private String url;

    @Value("${app.allowed_port}")
    private String port;

    @Value("#{environment.CLIENT_ALLOWED_PORT}")
    private String systemAllowedPort;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        if (systemAllowedPort == null) {
            System.out.println("### Client allowed port: " + port);
        } else {
            System.out.println("### Client allowed port: " + systemAllowedPort);
        }

        registry.addEndpoint("/game")
                .setAllowedOrigins(url + ":" + ((systemAllowedPort == null) ? port : systemAllowedPort)).withSockJS();
    }
}
