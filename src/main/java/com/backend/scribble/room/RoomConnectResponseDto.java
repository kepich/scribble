package com.backend.scribble.room;

import com.backend.scribble.player.Player;

public class RoomConnectResponseDto {

    public RoomConnectResponseDto(Player player, Room room) {
        this.player = player;
        this.room = room;
    }

    public Player player;
    public Room room;
}
