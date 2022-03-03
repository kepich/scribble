package com.backend.scribble.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameEvent {

    public String eventType;

    public GameEvent(
            @JsonProperty("eventType") String eventType
    ) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType='" + eventType + '\'' +
                '}';
    }
}
