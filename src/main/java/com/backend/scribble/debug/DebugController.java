package com.backend.scribble.debug;

import com.backend.scribble.player.Player;
import com.backend.scribble.room.Room;
import com.backend.scribble.player.PlayerService;
import com.backend.scribble.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("debug")
@ConditionalOnProperty("app.is_debugging")
@RestController
public class DebugController {
    @Autowired
    PlayerService playerService;

    @Autowired
    RoomService roomService;

    @GetMapping("all-players")
    public List<Player> getAllPlayers() {
        return roomService.getAllRooms().stream().map(Room::getPlayers).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @GetMapping("all-rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
