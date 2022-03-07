package com.backend.scribble.config;

import com.backend.scribble.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.List;
import java.util.Map;

@Component
public class SessionConnectEventListener implements ApplicationListener<SessionConnectedEvent> {
    @Autowired
    private RoomService roomService;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        Map<String, List<String>> nativeHeaders = getNativeHeaders(event);

        String roomId = nativeHeaders.get("roomId").get(0);
        String playerId = nativeHeaders.get("playerId").get(0);
        String sessionId = getSessionId(event);

        try {
            roomService.getRoomById(roomId)
                    .getPlayers().stream()
                    .filter(p -> p.getId().equals(playerId))
                    .findFirst().orElseThrow(
                            () -> new Exception("Player with id=" + playerId + " not found in room=" + roomId))
                    .setSessionId(sessionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, List<String>> getNativeHeaders(SessionConnectedEvent event) {
        return (Map<String, List<String>>)((GenericMessage)event.getMessage().getHeaders().get("simpConnectMessage"))
                .getHeaders().get("nativeHeaders");
    }

    private String getSessionId(SessionConnectedEvent event) {
        return (String) event.getMessage().getHeaders().get("simpSessionId");
    }
}