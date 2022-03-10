package com.backend.scribble.service.storage;

import com.backend.scribble.model.room.Room;
import com.backend.scribble.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomRepository {
    private final TreeMap<String, Room> rooms = new TreeMap<>();
    private final TreeMap<String, Room> roomsActive = new TreeMap<>();

    @Autowired
    private SocketService socketService;

    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    public Optional<Room> getById(String id) {
        return Optional.ofNullable(rooms.getOrDefault(id, null));
    }

    public List<Room> getAll() {
        return new ArrayList<>(rooms.values());
    }

    public Optional<Room> getByPlayerSessionId(String sessionId) {
        return rooms.entrySet().stream().filter(
                room -> room.getValue().getPlayers().stream().anyMatch(player -> sessionId.equals(player.getSessionId()))
        ).findFirst().map(Map.Entry::getValue);
    }

    public void deleteById(String id) {
        rooms.remove(id);
        roomsActive.remove(id);
    }

    public void startGame(Room room) {
        roomsActive.put(room.getId(), room);
        socketService.sendStartGame(room.getId());
    }
}
