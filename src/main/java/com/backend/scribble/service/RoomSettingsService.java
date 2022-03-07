package com.backend.scribble.service;

import com.backend.scribble.model.roomSettings.RoomSettings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomSettingsService {
    private final List<String> defaultWords = List.of();

    public RoomSettings getDefaultRoomSettings() {
        return new RoomSettings(3, 80, defaultWords, false);
    }
}
