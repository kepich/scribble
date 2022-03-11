package com.backend.scribble.api;

import com.backend.scribble.model.roomSettings.RoomSettings;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app/game")
@ConditionalOnProperty("app.is_debugging")
@RestController
public class APIProducersWS {

    @PostMapping("/settings/{room}")
    public void updateSettings(@PathVariable String room, @RequestBody RoomSettings roomSettings) {
    }

    @PostMapping("/start-game/{room}")
    public void startGame(@PathVariable String room) {
    }
}
