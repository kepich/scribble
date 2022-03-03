package com.backend.scribble.room;

import com.backend.scribble.player.Player;
import com.backend.scribble.player.PlayerDto;
import com.backend.scribble.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
public class RoomService {
    private final TreeMap<String, Room> rooms = new TreeMap<>();

    @Autowired
    private PlayerService playerService;

    public Room createRoom(PlayerDto playerDto) {
        Player newPlayer = playerService.createPlayer(playerDto.name);
        Room newRoom = new Room(String.valueOf(rooms.size()), newPlayer);
        newRoom.getPlayers().add(newPlayer);
        rooms.put(newRoom.getRoomId(), newRoom);
        return newRoom;
    }

    public Room connectToRoom(RoomConnectDto roomConnectDto) {
        Player newPlayer = playerService.createPlayer(roomConnectDto.player.name);
        Room room = rooms.get(roomConnectDto.roomId);
        room.getPlayers().add(newPlayer);
        return room;
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }

    public boolean isRoomExists(String room) {
        return rooms.containsKey(room);
    }
}
