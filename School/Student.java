package com.company;

public class Student
{
    private final String firstName;
    private final String familyName;
    private School school;

    Student(String firstName, String familyName) {
        if (firstName != "") {
            this.firstName = firstName;
        } else {
            this.firstName = "Anonymous";
        }
        if (firstName != "") {
            this.familyName = familyName;
        } else {
            this.familyName = "Student";
        }
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getFamilyName(){
        return this.familyName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
