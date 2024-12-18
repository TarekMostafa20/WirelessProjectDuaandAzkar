package com.example.wireless_project_duaandazkar;

public class Dua {
    private final String text;
    private final String audioUrl;
    private final String pronunciation;

    public Dua(String text, String audioUrl, String pronunciation) {
        this.text = text;
        this.audioUrl = audioUrl;
        this.pronunciation = pronunciation;
    }

    public String getText() {
        return text;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getPronunciation() {
        return pronunciation;
    }
}