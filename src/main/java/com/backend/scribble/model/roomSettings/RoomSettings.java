package com.backend.scribble.model.roomSettings;

import java.util.List;

public class RoomSettings {

    private final int roundNumber;
    private final int drawTime;
    private final List<String> words;
    private final boolean customWordsExcl;
    
    public RoomSettings(int roundNumber, int drawTime, List<String> words, boolean customWordsExcl) {
        this.roundNumber = roundNumber;
        this.drawTime = drawTime;
        this.words = words;
        this.customWordsExcl = customWordsExcl;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getDrawTime() {
        return drawTime;
    }

    public List<String> getWords() {
        return words;
    }

    public boolean isCustomWordsExcl() {
        return customWordsExcl;
    }

    @Override
    public String toString() {
        return "RoomSettings{" +
                "roundNumber=" + roundNumber +
                ", drawTime=" + drawTime +
                ", words=" + words +
                ", customWordsExcl=" + customWordsExcl +
                '}';
    }
}
