package com.backend.scribble.controller;

import com.backend.scribble.model.player.PlayerDto;
import com.backend.scribble.model.room.RoomConnectDto;
import com.backend.scribble.model.room.RoomConnectResponseDto;
import com.backend.scribble.service.RoomService;
import com.backend.scribble.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private SocketService socketService;

    @PostMapping("/create")
    public RoomConnectResponseDto createRoom(@RequestBody PlayerDto playerDto) {
        return roomService.createRoomAndConnect(playerDto);
    }

    @PostMapping("/connect")
    public RoomConnectResponseDto connectToRoom(@RequestBody RoomConnectDto roomConnectDto) throws Exception {
        RoomConnectResponseDto response = roomService.connectToTheRoom(roomConnectDto);
        socketService.sendConnect(response.room.getId(), response.player);
        return response;
    }
}
