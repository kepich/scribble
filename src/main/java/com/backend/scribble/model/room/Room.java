package com.backend.scribble.model.room;

import com.backend.scribble.model.player.Player;
import com.backend.scribble.model.roomSettings.RoomSettings;

import java.util.ArrayList;

public class Room {
    private final String id;
    private String owner;
    private final ArrayList<Player> players;
    private RoomSettings settings;

    public Room(String id, String owner, RoomSettings settings) {
        this.id = id;
        this.owner = owner;
        this.players = new ArrayList<>();
        this.settings = settings;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public RoomSettings getSettings() {
        return settings;
    }

    public void setSettings(RoomSettings settings) {
        this.settings = settings;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                ", players=" + players +
                ", roomSettings=" + settings +
                '}';
    }

}
