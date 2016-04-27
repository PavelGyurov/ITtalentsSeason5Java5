package com.company;

public class Main {

    public static void main(String[] args) {

        School dobrivoinikov = new School("Dobri Voinikov", 3, 60, 6);
        Teacher tabakova = new Teacher("idiotka", "tabakova", 2);
        Teacher didka = new Teacher("didka", "vodenicharova", 2);
        Teacher djurina = new Teacher("frau", "djurina", 2);

        dobrivoinikov.addTeacher(tabakova);
        dobrivoinikov.addTeacher(didka);
        dobrivoinikov.addTeacher(djurina);

        Group matemamatika = new Group("matemamatika", tabakova, 10);
        Group belo = new Group("belo", tabakova, 10);
        dobrivoinikov.addGroup(matemamatika);
        dobrivoinikov.addGroup(belo);

        matemamatika.addStudent(new Student("rosen", "Sharkov"));
        matemamatika.addStudent(new Student("kalo", "vaasev"));
        matemamatika.addStudent(new Student("sasheto", "ivanoff"));
        matemamatika.addStudent(new Student("petard", "tapira"));
        matemamatika.addStudent(new Student("miroslav", "stoyanob"));
        matemamatika.addStudent(new Student("yagodka", "teneva"));

        belo.addStudent(new Student("kol", "stan"));
        belo.addStudent(new Student("djordjano", "djordano"));
        belo.addStudent(new Student("krasimir", "stoev"));

        tabakova.printTeacherInfo();
    }
}
