package com.backend.scribble.service;

import com.backend.scribble.event.EventType;
import com.backend.scribble.model.player.Player;
import com.backend.scribble.model.room.Room;
import com.backend.scribble.model.roomSettings.RoomSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendConnect(String room, Player resp) {
        simpMessagingTemplate.convertAndSend(getDestination(EventType.CONNECT.getSubscribeName(), room), resp);
    }

    public void sendSettings(String room, RoomSettings resp) {
        simpMessagingTemplate.convertAndSend(getDestination(EventType.SETTINGS.getSubscribeName(), room), resp);
    }

    public void sendStartGame(String room) {
        simpMessagingTemplate.convertAndSend(getDestination(EventType.START_GAME.getSubscribeName(), room), room);
    }

    public void sendDisconnect(String room, String resp) {
        simpMessagingTemplate.convertAndSend(getDestination(EventType.DISCONNECT.getSubscribeName(), room), resp);
    }

    public void sendUpdateRoom(Room room) {
        simpMessagingTemplate.convertAndSend(getDestination(EventType.UPDATE_ROOM.getSubscribeName(), room.getId()), room);
    }

    private String getDestination(String topicName, String room) {
        return topicName + room;
    }
}
