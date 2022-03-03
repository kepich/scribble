package com.backend.scribble.game;

import com.backend.scribble.player.Player;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameEvent {

    public String eventType;
    public Player player;

    public GameEvent(
            @JsonProperty("eventType") String eventType,
            @JsonProperty("player") Player player
    ) {
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
