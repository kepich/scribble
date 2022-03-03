package com.backend.scribble.room;

import com.backend.scribble.player.Player;

import java.util.ArrayList;

public class Room {
    private String roomId;
    private Player owner;
    private ArrayList<Player> players;
    // TODO: Room settings

    public Room(String roomId, Player owner) {
        this.roomId = roomId;
        this.owner = owner;
        this.players = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
