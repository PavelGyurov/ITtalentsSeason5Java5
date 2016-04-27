package com.company;


public class Patient extends Person{

    public enum Gender{Male, Female}

    private Gender gender;
    private int age;
    private Doctor doctor;
    private MedicalPlan medicalPlan;
    private int roomNumber;
    private Department department;

    public Patient(String firstName, String lastName, String phoneNumber, Gender gender, int age) {
        super(firstName, lastName, phoneNumber);
        this.gender = gender;
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public MedicalPlan getMedicalPlan() {
        return medicalPlan;
    }

    public void setMedicalPlan(MedicalPlan medicalPlan) {
        this.medicalPlan = medicalPlan;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
