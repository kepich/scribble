package com.backend.scribble.game;

import com.backend.scribble.player.Player;

public class GameEvent {

    public String eventType;
    public Player player;

    public GameEvent(String eventType, Player player) {
        this.eventType = eventType;
        this.player = player;
    }

    @Override
    public String toString() {
        return "GameEvent{" +
                "eventType='" + eventType + '\'' +
                ", player=" + player +
                '}';
    }
}
