package com.backend.scribble.room;

import com.backend.scribble.player.Player;
import com.backend.scribble.room.settings.RoomSettings;

import java.util.ArrayList;

public class Room {
    private String roomId;
    private Player owner;
    private ArrayList<Player> players;
    private RoomSettings roomSettings;

    public Room(String roomId, Player owner, RoomSettings roomSettings) {
        this.roomId = roomId;
        this.owner = owner;
        this.players = new ArrayList<>();
        this.roomSettings = roomSettings;
    }

    public String getRoomId() {
        return roomId;
    }

    public Player getOwner() {
        return owner;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public RoomSettings getRoomSettings() {
        return roomSettings;
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", owner=" + owner +
                ", players=" + players +
                ", roomSettings=" + roomSettings +
                '}';
    }

}
