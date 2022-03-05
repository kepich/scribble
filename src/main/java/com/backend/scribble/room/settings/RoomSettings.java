package com.backend.scribble.room.settings;

import java.util.List;

public class RoomSettings {

    private int roundNumber;
    private int drawTime;
    private List<String> words;
    private boolean customWordsExcl;
    
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
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static class Builder {
        private int roundNumber;
        private int drawTime;
        private List<String> words;
        private boolean customWordsExcl;
        
        private Builder() {}

        public Builder setRoundNumber(int roundNumber) {
            this.roundNumber = roundNumber;
            return this;
        }

        public Builder setDrawTime(int drawTime) {
            this.drawTime = drawTime;
            return this;
        }

        public Builder setWords(List<String> words) {
            this.words = words;
            return this;
        }

        public Builder setCustomWordsExcl(boolean customWordsExcl) {
            this.customWordsExcl = customWordsExcl;
            return this;
        }
        
        public RoomSettings build() {
            return new RoomSettings(this.roundNumber, this.drawTime, this.words, this.customWordsExcl);
        }
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
