package com.backend.scribble.event;

public enum EventType {
    SETTINGS("settings"),
    CONNECT("connect"),
    DISCONNECT("disconnect"),
    START_GAME("start-game"),
    UPDATE_ROOM("update-room"),
    ;

    public final String topicName;
    EventType(String topicName) {
        this.topicName = topicName;
    }

    public String getSubscribeName() {
        return "/topic/" + topicName + "/";
    }

    public String getProducerName() {
        return "/app/game/" + topicName + "/";
    }
}
