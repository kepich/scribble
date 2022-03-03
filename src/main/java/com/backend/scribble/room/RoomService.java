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


    public Room createRoom(Player player) {
        Room newRoom = new Room(String.valueOf(rooms.size()), player);
        newRoom.getPlayers().add(player);
        rooms.put(newRoom.getRoomId(), newRoom);
        return newRoom;
    }

    public Room connectToRoom(Player player, String roomId) {
        Room room = rooms.get(roomId);
        room.getPlayers().add(player);
        return room;
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }

    public boolean isRoomExists(String room) {
        return rooms.containsKey(room);
    }
}
