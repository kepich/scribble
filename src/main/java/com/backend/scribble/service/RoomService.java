package com.backend.scribble.service;

import com.backend.scribble.event.EventType;
import com.backend.scribble.model.player.Player;
import com.backend.scribble.model.player.PlayerDto;
import com.backend.scribble.model.room.Room;
import com.backend.scribble.model.room.RoomConnectDto;
import com.backend.scribble.model.room.RoomConnectResponseDto;
import com.backend.scribble.model.roomSettings.RoomSettings;
import com.backend.scribble.service.PlayerService;
import com.backend.scribble.service.RoomSettingsService;
import com.backend.scribble.service.SocketService;
import com.backend.scribble.service.storage.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

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

    public RoomConnectResponseDto connectToTheRoom(RoomConnectDto roomConnectDto) throws Exception {
        Player newPlayer = playerService.createPlayer(roomConnectDto.player.name);
        Room room = connectToRoom(newPlayer, roomConnectDto.roomId);

        System.out.println("Connect to room: " + room.toString());

        return new RoomConnectResponseDto(newPlayer, room);
    }

    private Room createRoomForPlayer(Player player) {
        Room newRoom = new Room(UUID.randomUUID().toString(), player.getId(), roomSettingsService.getDefaultRoomSettings());
        newRoom.getPlayers().add(player);
        roomRepository.addRoom(newRoom);
        return newRoom;
    }

    private Room connectToRoom(Player player, String roomId) throws Exception {
        Room room = roomRepository.getById(roomId).orElseThrow(() -> new Exception("Room with id=" + roomId + " not found"));
        room.getPlayers().add(player);
        return room;
    }

    public List<Room> getAllRooms() {
        return roomRepository.getAll();
    }

    public void setRoomSettings(String room, RoomSettings roomSettings) throws Exception {
        getRoomOptional(room)
                .orElseThrow(() -> new Exception("Room with id=" + room + " does not exists"))
                .setSettings(roomSettings);
    }

    private Optional<Room> getRoomOptional(String room) {
        return roomRepository.getById(room);
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
        roomRepository.getByPlayerSessionId(sessionId).ifPresent(room ->
            room.getPlayers().stream()
                    .filter(player -> sessionId.equals(player.getSessionId()))
                    .findFirst()
                    .ifPresent(player -> {
                        removePlayerFromRoom(player, room);
                        socketService.sendDisconnect(room.getId(), player.getId());
                    }
        ));
    }

    public void startGame(String roomId) {
        roomRepository.getById(roomId).ifPresent(room -> roomRepository.startGame(room));
    }

    private void removePlayerFromRoom(Player player, Room room) {
        room.getPlayers().remove(player);

        if (room.getPlayers().isEmpty()) {
            roomRepository.deleteById(room.getId());
        } else {
            updateAdmin(room);
        }
    }

    private void updateAdmin(Room room) {
        room.setOwner(room.getPlayers().get(0).getId());
        socketService.sendUpdateRoom(room);
    }
}
