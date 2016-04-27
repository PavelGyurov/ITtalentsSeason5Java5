package com.company;

public class Group {

    private String name;
    private Teacher teacher;
    private Student[] students;
    private int freePlaces;
    private School school;

    Group(String name, Teacher teacher, int freePlaces){
        if (name != "") {
            this.name = name;
        } else {
            this.name = "Group";
        }
        this.teacher = teacher;
        if (freePlaces > 0) {
            this.students = new Student[freePlaces];
            this.freePlaces = freePlaces;
        } else {
            this.students = new Student[10];
            this.freePlaces = 10;
        }
        teacher.addGroup(this);
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
       if (teacher.getFreeGroupPlaces() > 0) {
           this.teacher = teacher;
           System.out.println(teacher.getFirstName() + " " + teacher.getFamilyName() + " has been assigned " + this.name);
           //teacher.addGroup(this);
       } else {
           System.out.println("This teacher is overworked");
       }
    }

    public String getName() {
        return name;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setName(String name) {
        if (name != "") {
            this.name = name;
        }

    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void addStudent(Student student) {
        if (this.freePlaces > 0 && this.getSchool().getStudentFreePlaces() > 0) {
            this.students[this.students.length - this.freePlaces] = student;
            this.freePlaces--;
            this.getSchool().addStudent(student);
            System.out.println(student.getFirstName() + " has been added to " + this.name);
        } else {
            System.out.println("Cannot add student, group is full.");
        }
    }
}
