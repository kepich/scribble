package com.backend.scribble.model.player;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerDto {
    @JsonProperty("name")
    public String name;
}
