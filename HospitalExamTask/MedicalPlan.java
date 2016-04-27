package com.company;

import java.util.HashSet;

public class MedicalPlan {

    private String diagnose;
    private Patient patient;
    private int daysOfTreatment;
    private HashSet<String> medications;
    private int daysLeft;
    private boolean takenMeds;

    public MedicalPlan(String diagnose, Patient patient, int daysOfTreatment, HashSet<String> medications) {
        this.diagnose = diagnose;
        this.patient = patient;
        this.daysOfTreatment = daysOfTreatment;
        this.medications = medications;
        this.daysLeft = daysOfTreatment;
        takenMeds = false;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getDaysOfTreatment() {
        return daysOfTreatment;
    }

    public HashSet<String> getMedications() {
        return medications;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public boolean isTakenMeds() {
        return takenMeds;
    }

    public void setTakenMeds(boolean takenMeds) {
        this.takenMeds = takenMeds;
    }
}
