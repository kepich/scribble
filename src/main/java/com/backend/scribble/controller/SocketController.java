package com.backend.scribble.controller;

import com.backend.scribble.service.RoomService;
import com.backend.scribble.model.roomSettings.RoomSettings;
import com.backend.scribble.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MessageMapping("/game")
public class SocketController {
    @Autowired
    private SocketService socketService;

    @Autowired
    private RoomService roomService;


    @MessageMapping("/settings/{room}")
    public void updateSettings(@DestinationVariable String room, RoomSettings roomSettings) {
        System.out.println("Recieve settings: " + roomSettings.toString() + ", room: " + room);
        try {
            roomService.setRoomSettings(room, roomSettings);
            socketService.sendSettings(room, roomSettings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @MessageMapping("/start-game/{room}")
    public void startGame(@DestinationVariable String room) {
        System.out.println("Start game" + ", room: " + room);
        try {
            roomService.startGame(room);
            socketService.sendStartGame(room);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
