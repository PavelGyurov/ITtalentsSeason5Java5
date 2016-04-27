package com.company;

import java.util.HashSet;

public class Room {

    private HashSet<Patient> patients;
    private boolean hasPatient;
    private Patient.Gender genderOfPatients;
    private int number;

    public Room(int number) {
        this.hasPatient = false;
        patients = new HashSet<Patient>();
        this.number = number;
    }

    public HashSet<Patient> getPatients() {
        return patients;
    }

    public boolean isHasPatient() {
        return hasPatient;
    }

    public Patient.Gender getGenderOfPatients() {
        return genderOfPatients;
    }

    public void setHasPatient(boolean hasPatient) {
        this.hasPatient = hasPatient;
    }

    public int getNumber() {
        return number;
    }

    public synchronized boolean putPatient(Patient patient){
        if (patients.isEmpty()){
            patients.add(patient);
            hasPatient = true;
            this.genderOfPatients = patient.getGender();
            patient.setRoomNumber(number);
            return true;
        }else {
            if (patients.size() < 3){
                if (patient.getGender() == genderOfPatients){
                    patients.add(patient);
                    patient.setRoomNumber(number);
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized void removePatient(Patient patient){
        patients.remove(patient);
        if (patients.isEmpty()){
            genderOfPatients = null;
            hasPatient = false;
        }
    }
}
