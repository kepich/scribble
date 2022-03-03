package com.backend.scribble.socket;

import com.backend.scribble.game.Event;
import com.backend.scribble.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RoomService roomService;

    @MessageMapping("/game/{room}")
    public void sendMessage(@DestinationVariable String room, Event event) {
        System.out.println("Recieve event: " + event.toString() + ", room: " + room);

        if (roomService.isRoomExists(room)) {
            // Routine...
            simpMessagingTemplate.convertAndSend("/topic/messages/" + room, event);
        }

    }
}
