package com.backend.scribble.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

    public String eventType;

    public Event(
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
