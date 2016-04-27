package com.company;

public class School {

    private final String name;
    private Teacher[] teachers;
    private Student[] students;
    private Group[] groups;
    private int teacherFreePlaces;
    private int studentFreePlaces;
    private int groupFreePlaces;

    public School(String name, int teacherFreePlaces, int studentFreePlaces, int groupFreePlaces) {
        if (name != "") {
            this.name = name;
        } else {
            this.name = "School";
        }
        if (teacherFreePlaces > 0) {
        this.teacherFreePlaces = teacherFreePlaces;
            teachers = new Teacher[teacherFreePlaces];
        } else {
            this.teacherFreePlaces = 10;
            teachers = new Teacher[10];
        }
        if (studentFreePlaces > 0) {
            this.studentFreePlaces = studentFreePlaces;
            students = new Student[studentFreePlaces];
        } else {
            this.studentFreePlaces = 200;
            students = new Student[200];
        }
        if (groupFreePlaces > 0) {
            this.groupFreePlaces = groupFreePlaces;
            groups = new Group[groupFreePlaces];
        } else {
            this.groupFreePlaces = 20;
            groups = new Group[20];
        }
    }

    public String getName() {
        return name;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    public Student[] getStudents() {
        return students;
    }

    public Group[] getGroups() {
        return groups;
    }

    public int getStudentFreePlaces() {
        return studentFreePlaces;
    }

    public void addTeacher(Teacher teacher){
        if (teacherFreePlaces > 0) {
            teachers[teachers.length - teacherFreePlaces] = teacher;
            teacherFreePlaces--;
            System.out.println(teacher.getFirstName() + teacher.getFamilyName() + " has been hired by " + this.name);
        } else {
            System.out.println("Cannot hire teacher, no more available positions");
        }
    }

    public void addGroup(Group group){
        if (groupFreePlaces > 0) {
            groups[groups.length - groupFreePlaces] = group;
            group.setSchool(this);
        } else {
            System.out.println("Cannot add group");
        }
    }

    public void addStudent(Student student){
        if (studentFreePlaces > 0){
            students[students.length - studentFreePlaces] = student;
            studentFreePlaces--;
        }
    }
}
