package com.backend.scribble.room;

import com.backend.scribble.player.Player;
import com.backend.scribble.player.PlayerDto;
import com.backend.scribble.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

@Service
public class RoomService {
    private final TreeMap<String, Room> rooms = new TreeMap<>();

    @Autowired
    private PlayerService playerService;

    public RoomConnectResponseDto createRoomAndConnect(PlayerDto playerDto) {
        Player newPlayer = playerService.createPlayer(playerDto.name);
        Room room = createRoomForPlayer(newPlayer);

        System.out.println("Create room: " + room.toString());

        return new RoomConnectResponseDto(newPlayer, room);
    }

    public RoomConnectResponseDto connectToTheRoom(RoomConnectDto roomConnectDto) {
        Player newPlayer = playerService.createPlayer(roomConnectDto.player.name);
        Room room = connectToRoom(newPlayer, roomConnectDto.roomId);

        System.out.println("Connect to room: " + room.toString());

        return new RoomConnectResponseDto(newPlayer, room);
    }

    private Room createRoomForPlayer(Player player) {
        Room newRoom = new Room(UUID.randomUUID().toString(), player);
        newRoom.getPlayers().add(player);
        rooms.put(newRoom.getRoomId(), newRoom);
        return newRoom;
    }

    private Room connectToRoom(Player player, String roomId) {
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
