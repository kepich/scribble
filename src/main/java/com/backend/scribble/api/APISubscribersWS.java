package com.backend.scribble.api;

import com.backend.scribble.model.player.Player;
import com.backend.scribble.model.room.Room;
import com.backend.scribble.model.roomSettings.RoomSettings;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

@RequestMapping("topic")
@ConditionalOnProperty("app.is_debugging")
@RestController
public class APISubscribersWS {

    @GetMapping("settings")
    public RoomSettings settings() {
        return null;
    }

    @GetMapping("connect")
    public Player connect() {
        return null;
    }

    @GetMapping("disconnect")
    public String disconnect() {
        return null;
    }

    @GetMapping("start-game")
    public String startGame() {
        return null;
    }

    @GetMapping("update-room")
    public Room updateRoom() {
        return null;
    }
}
