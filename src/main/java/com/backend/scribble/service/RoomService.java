package com.backend.scribble.service;

import com.backend.scribble.event.EventType;
import com.backend.scribble.model.player.Player;
import com.backend.scribble.model.player.PlayerDto;
import com.backend.scribble.model.room.Room;
import com.backend.scribble.model.room.RoomConnectDto;
import com.backend.scribble.model.room.RoomConnectResponseDto;
import com.backend.scribble.model.roomSettings.RoomSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final TreeMap<String, Room> rooms = new TreeMap<>();

    @Autowired
    private PlayerService playerService;

    @Autowired
    private RoomSettingsService roomSettingsService;

    @Autowired
    private SocketService socketService;

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
        Room newRoom = new Room(UUID.randomUUID().toString(), player.getId(), roomSettingsService.getDefaultRoomSettings());
        newRoom.getPlayers().add(player);
        rooms.put(newRoom.getId(), newRoom);
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

    public void setRoomSettings(String room, RoomSettings roomSettings) throws Exception {
        getRoomOptional(room)
                .orElseThrow(() -> new Exception("Room with id=" + room + " does not exists"))
                .setSettings(roomSettings);
    }

    private Optional<Room> getRoomOptional(String room) {
        return Optional.ofNullable(rooms.getOrDefault(room, null));
    }

    public Room getRoomById(String room) throws Exception {
        return getRoomOptional(room)
                .orElseThrow(() -> new Exception("Room with id=" + room + " does not exists"));
    }

    public List<Player> getAllPlayers() {
        return getAllRooms().stream()
                .map(Room::getPlayers)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void disconnectPlayerBySession(String sessionId) {
        Optional<Player> playerOp;
        for(Map.Entry<String, Room> room: rooms.entrySet()) {
            playerOp = room.getValue().getPlayers().stream().filter(p -> p.getSessionId().equals(sessionId)).findFirst();
            if (playerOp.isPresent()) {
                room.getValue().getPlayers().remove(playerOp.get());
                socketService.send(EventType.DISCONNECT, room.getValue().getId(), playerOp.get().getId());
                return;
            }
        }
    }
}
