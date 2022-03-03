package com.backend.scribble.room;

import com.backend.scribble.player.Player;
import com.backend.scribble.player.PlayerDto;
import com.backend.scribble.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public Room createRoom(@RequestBody PlayerDto playerDto) throws Exception {
        Room room = roomService.createRoom(playerDto);
        System.out.println("Create room: " + room.toString());
        return room;
    }

    @PostMapping("/connect")
    public Room connectToRoom(@RequestBody RoomConnectDto roomConnectDto) {
        Room room = roomService.connectToRoom(roomConnectDto);
        System.out.println("Connect to room: " + room.toString());
        return room;
    }
}
