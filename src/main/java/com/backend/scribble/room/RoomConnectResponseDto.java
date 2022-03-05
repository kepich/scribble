package com.backend.scribble.room;

import com.backend.scribble.player.Player;

public class RoomConnectResponseDto {

    public Player player;
    public Room room;

    public RoomConnectResponseDto(Player player, Room room) {
        this.player = player;
        this.room = room;
    }

    @Override
    public String toString() {
        return "RoomConnectResponseDto{" +
                "player=" + player +
                ", room=" + room +
                '}';
    }
}
