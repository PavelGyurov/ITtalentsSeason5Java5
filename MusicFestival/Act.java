package com.company;

import java.time.Duration;
import java.time.LocalDate;

public class Act {

    private Band band;
    private Song[] songs;
    private LocalDate start;
    private LocalDate end;
    private Duration duration = Duration.between(this.start, this.end);



    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Duration getDuration() {
        return duration;
    }

    public Band getBand() {
        return band;
    }

    public void getSetlist(){
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null){
                System.out.println(songs[i].getTitle());
            }
        }
    }

    public void playSet(){
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null){
                band.play(songs[i]);
            }
        }
    }
}
