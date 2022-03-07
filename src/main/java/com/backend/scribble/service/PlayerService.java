package com.backend.scribble.service;

import com.backend.scribble.model.player.Player;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PlayerService {
    public Player createPlayer(String playerName) {
        return new Player(UUID.randomUUID().toString(), playerName);
    }
}
