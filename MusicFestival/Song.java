package com.company;

public class Song {

    private String title;
    private String lyrics;
    private Band performer;
    private boolean isInsrumental;


    public Song(String title, Band performer) {
        if (title != ""){
            this.title = title;
        }
        if (performer != null) {
            this.performer = performer;
        }
        isInsrumental = true;
    }

    public Song(String title, String lyrics, Band performer) {
        this(title, performer);
        if (lyrics != ""){
            this.lyrics = lyrics;
            isInsrumental = false;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public Band getPerformer() {
        return performer;
    }

    public boolean isInsrumental() {
        return isInsrumental;
    }
}
