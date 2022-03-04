package com.backend.scribble.room;

import com.backend.scribble.player.PlayerDto;
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

    @PostMapping("/create")
    public RoomConnectResponseDto createRoom(@RequestBody PlayerDto playerDto) {
        return roomService.createRoomAndConnect(playerDto);
    }

    @PostMapping("/connect")
    public RoomConnectResponseDto connectToRoom(@RequestBody RoomConnectDto roomConnectDto) {
        return roomService.connectToTheRoom(roomConnectDto);
    }
}
