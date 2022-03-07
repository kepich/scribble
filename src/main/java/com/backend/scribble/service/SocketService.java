package com.backend.scribble.service;

import com.backend.scribble.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void send(EventType eventType, String room, Object response) {
        simpMessagingTemplate.convertAndSend(getDestination(eventType.getSubscribeName(), room), response);
    }

    private String getDestination(String topicName, String room) {
        return topicName + room;
    }
}
