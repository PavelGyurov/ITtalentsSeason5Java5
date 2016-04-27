package com.company;

public class Teacher {
    private final String firstName;
    private final String familyName;
    private Group[] teachingGroups;
    private int freeGroupPlaces;
    private School school;

    Teacher(String firstName, String familyName, int freeGroupPlaces) {
        if (firstName != "") {
            this.firstName = firstName;
        } else {
            this.firstName = "Anonymous";
        }
        if (firstName != "") {

            this.familyName = familyName;
        } else {
            this.familyName = "Teacher";
        }
        if (freeGroupPlaces > 0) {
            this.teachingGroups = new Group[freeGroupPlaces];
            this.freeGroupPlaces = freeGroupPlaces;
        } else {
            this.teachingGroups = new Group[10];
            this.freeGroupPlaces = 10;
        }
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getFamilyName(){
        return this.familyName;
    }

    public Group[] getTeachingGroups() {
        return teachingGroups;
    }

    public void addGroup(Group newGroup){
        if (freeGroupPlaces > 0){
            teachingGroups[teachingGroups.length - freeGroupPlaces] = newGroup;
            freeGroupPlaces--;
            newGroup.setTeacher(this);
        } else {
            System.out.println("Cannot add group, teacher has no more time.");
        }
    }

    public int getFreeGroupPlaces() {
        return freeGroupPlaces;
    }

    public School getSchool() {
        return school;
    }

    public void printTeacherInfo(){
        System.out.println("Teacher name: " + firstName + " " + familyName);
        System.out.println("---------");
        System.out.println("Groups: ");
        for (int i = 0; i < teachingGroups.length; i++) {
            if (teachingGroups[i] == null) {
                break;
            } else {
                System.out.println(teachingGroups[i].getName());
                System.out.println("Students in group:");
                for (int j = 0; j < teachingGroups[i].getStudents().length; j++) {
                    if (teachingGroups[i].getStudents()[j] == null) {
                        break;
                    } else {
                        System.out.println("    " + teachingGroups[i].getStudents()[j].getFirstName() + " " + teachingGroups[i].getStudents()[j].getFamilyName());
                    }
                }
            }
        }
    }
}
