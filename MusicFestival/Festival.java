package com.company;

import java.time.LocalDate;

public class Festival {

    private LocalDate start;
    private String location;
    private Act[] acts;

    public Festival(LocalDate start, String location, Act[] acts) {
        this.start = start;
        this.location = location;
        this.acts = acts;
    }

    public LocalDate getStart() {
        return start;
    }

    public String getLocation() {
        return location;
    }

    public void viewActs(){
        for (int i = 0; i < acts.length; i++) {
            if (acts[i] != null){
                System.out.println(acts[i].getBand());
                acts[i].getSetlist();
                System.out.println("-------------");
            }
        }
    }
}
