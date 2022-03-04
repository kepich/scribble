package com.backend.scribble.player;

import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PlayerService {
    public Player createPlayer(String playerName) {
        return new Player(UUID.randomUUID().toString(), playerName);
    }
}
