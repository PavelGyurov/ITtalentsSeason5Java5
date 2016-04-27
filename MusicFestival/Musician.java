package com.company;

public class Musician {

    private String name;
    private String instrument;

    public Musician(String name) {
        if (name != "") {
            this.name = name;
        } else {
            this.name = "Nameless ghoul";
        }
    }

    public Musician(String name, String instrument) {
       this(name);
        if (instrument != ""){
            this.instrument = instrument;
        } else {
            this.instrument = "Finger snapping";
        }
    }

    public String getName() {
        return name;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        if (instrument != ""){
            this.instrument = instrument;
        }
    }

    public void play(Song song){
        System.out.println(name + " plays " + instrument + " on " + song.getTitle());
    }
}
