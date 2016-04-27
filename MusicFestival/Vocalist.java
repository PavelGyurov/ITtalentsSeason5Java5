package com.company;

public class Vocalist extends Musician{

    public Vocalist(String name) {
        super(name);
        this.setInstrument("Voice");
    }

    @Override
    public void play(Song song){
        if (song.isInsrumental()){
            System.out.println(this.getName() + " dances around the stage.");
        } else {
            System.out.println(this.getName() + " sings: \n" + song.getLyrics());
        }
    }
}
