package com.backend.scribble.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    private String id;
    private String name;

    public Player(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name
    ) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
