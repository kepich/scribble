package com.backend.scribble.config;

import com.backend.scribble.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    private RoomService roomService;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        roomService.disconnectPlayerBySession(event.getSessionId());
    }
}