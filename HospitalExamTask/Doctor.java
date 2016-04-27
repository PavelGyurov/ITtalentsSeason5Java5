package com.company;


import java.util.*;

public class Doctor extends Person{

    public String[] diagnoses = {"Broken bone", "Heart attack", "Influenza"};
    private String specialty;
    private HashSet<Patient> patients;
    private boolean isAvailable;


    public Doctor(String firstName, String lastName, String phoneNumber, String specialty) {
        super(firstName, lastName, phoneNumber);
        this.specialty = specialty;
        patients = new HashSet<Patient>();
        isAvailable = true;
    }

    public String getSpecialty() {
        return specialty;
    }

    public HashSet<Patient> getPatients() {
        return patients;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void addPatient(Patient patient){
        if (patients.size() < 5){
            patients.add(patient);
        }
        if (patients.size() == 5){
            isAvailable = false;
        }
    }

    public MedicalPlan createMedicalPlan(Patient patient){
        Random random = new Random();
        int diagnoseIndex = random.nextInt(3);
        int days = random.nextInt(3) + 3;
        int numberOfMeds = random.nextInt(4)+1;
        String[] medsList = {"Voltaren", "Aspirin", "Novolog", "Synthroid", "Nexium", "Lantus", "Tamiflu", "Neurofen", "Oxycontin", "Vesicare"};
        HashSet<String> meds = new HashSet<String>();
        for (int i = 0; i < numberOfMeds; i++) {
            meds.add(medsList[random.nextInt(10)]);
        }
        MedicalPlan medicalPlan = new MedicalPlan(diagnoses[diagnoseIndex], patient, days, meds);
        patient.setMedicalPlan(medicalPlan);
        return medicalPlan;
    }

    public synchronized void visitation(){
        Thread visitation = new Thread(new Runnable() {
            @Override
            public void run() {
                isAvailable = false;
                for (Patient p : patients){
                    System.out.println("Doctor " + getFirstName() + " " + getLastName() + " has visited patient " + p.getFirstName() + " " +
                    p.getLastName() + " in room number " + p.getRoomNumber() + " in section " + p.getDepartment().getDepartmentName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isAvailable = true;
            }
        });
        visitation.start();
    }
}
