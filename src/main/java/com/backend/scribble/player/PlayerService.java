package com.backend.scribble.player;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class PlayerService {
    public Player createPlayer(String playerName) {
        return new Player(playerName, playerName);
    }
}
