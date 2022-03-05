package com.backend.scribble.room.settings;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomSettingsService {
    private List<String> defaultWords;

    public RoomSettings getDefaultRoomSettings() {
        RoomSettings.newBuilder().setRoundNumber(0).setDrawTime(0).setWords(List.of()).setCustomWordsExcl(false).build();
        return RoomSettings.newBuilder()
                .setCustomWordsExcl(false)
                .setDrawTime(80)
                .setRoundNumber(3)
                .setWords(List.of())
                .build();
    }

    public RoomSettings getRoomSettings(int roundNumber, int drawTime, List<String> words, boolean customWordsExcl) {
        return RoomSettings.newBuilder()
                .setCustomWordsExcl(customWordsExcl)
                .setDrawTime(drawTime)
                .setRoundNumber(roundNumber)
                .setWords(customWordsExcl ? words : this.defaultWords)
                .build();
    }
}
