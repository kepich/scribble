package com.backend.scribble.config.debug;

import com.backend.scribble.model.player.Player;
import com.backend.scribble.model.room.Room;
import com.backend.scribble.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("debug")
@ConditionalOnProperty("app.is_debugging")
@RestController
public class DebugController {

    @Autowired
    RoomService roomService;

    @GetMapping("all-players")
    public List<Player> getAllPlayers() {
        return roomService.getAllPlayers();
    }

    @GetMapping("all-rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
