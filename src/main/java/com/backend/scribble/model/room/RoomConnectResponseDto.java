package com.backend.scribble.model.room;

import com.backend.scribble.api.API;
import com.backend.scribble.model.player.Player;

public class RoomConnectResponseDto {

    public Player player;
    public Room room;
    public API api;

    public RoomConnectResponseDto(Player player, Room room) {
        this.player = player;
        this.room = room;
        this.api = new API();
    }

    @Override
    public String toString() {
        return "RoomConnectResponseDto{" +
                "player=" + player +
                ", room=" + room +
                '}';
    }
}
