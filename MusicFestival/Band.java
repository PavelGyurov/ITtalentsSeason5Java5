package com.company;

public class Band {

    private Musician[] members;
    private int freePlaces;

    public void addMember(Musician member){
        if (this.freePlaces > 0){
            members[members.length-freePlaces] = member;
        }else {
            System.out.println("Band is full.");
        }
    }

    public void listMembers(){
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null){
                System.out.println(members[i].getName() + " - " + members[i].getInstrument());
            }
        }
    }

//    public boolean hasVocalist(){
//        boolean hasVocalist = false;
//        for (int i = 0; i < members.length; i++) {
//            if (members[i] != null){
//                if (members[i] instanceof Vocalist){
//                    hasVocalist = true;
//                }
//            }
//        }
//        return hasVocalist;
//    }

    public void play(Song song){
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null){
                members[i].play(song);
            }
        }
    }
}
