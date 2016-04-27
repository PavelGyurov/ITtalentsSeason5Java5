package com.company;


import java.util.*;

public class Department {


    public enum DepartmentName{Orthopedy, Cardiology, Virology}

    private DepartmentName departmentName;
    private Room[] rooms;
    private HashSet<Patient> patients;

    public Department(DepartmentName departmentName) {
        this.departmentName = departmentName;
        rooms = new Room[10];
        patients = new HashSet<Patient>();
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new Room(i+1);
        }
    }

    public DepartmentName getDepartmentName() {
        return departmentName;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public HashSet<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient){
        boolean hospitalised = false;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].putPatient(patient)){
                hospitalised = true;
                patients.add(patient);
                break;
            }
        }
        if (!hospitalised){
            System.out.println("Can't hospitalise patient.");
        }
    }

    public synchronized void removePatient(Patient patient){
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getPatients().contains(patient)){
                rooms[i].removePatient(patient);
            }
        }
        patients.remove(patient);
    }
}
