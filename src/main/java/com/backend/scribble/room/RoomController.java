package com.backend.scribble.room;

import com.backend.scribble.player.Player;
import com.backend.scribble.player.PlayerDto;
import com.backend.scribble.player.PlayerService;
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
    private PlayerService playerService;

    @PostMapping("/create")
    public RoomConnectResponseDto createRoom(@RequestBody PlayerDto playerDto) throws Exception {
        Player newPlayer = playerService.createPlayer(playerDto.name);
        Room room = roomService.createRoom(newPlayer);
        System.out.println("Create room: " + room.toString());
        return new RoomConnectResponseDto(newPlayer, room);
    }

    @PostMapping("/connect")
    public RoomConnectResponseDto connectToRoom(@RequestBody RoomConnectDto roomConnectDto) {
        Player newPlayer = playerService.createPlayer(roomConnectDto.player.name);
        Room room = roomService.connectToRoom(newPlayer, roomConnectDto.roomId);
        System.out.println("Connect to room: " + room.toString());
        return new RoomConnectResponseDto(newPlayer, room);
    }
}
