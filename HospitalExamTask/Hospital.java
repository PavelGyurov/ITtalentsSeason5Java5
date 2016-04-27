package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Hospital {

    private HashSet<Doctor> doctors;
    private HashSet<Nurse> nurses;
    private ArrayList<Department> departments;
    private CopyOnWriteArrayList<MedicalPlan> medicalPlans;
    private File medicalRecords;

    public Hospital() {
        doctors = new HashSet<Doctor>();
        nurses = new HashSet<Nurse>();
        departments = new ArrayList<Department>();
        medicalPlans = new CopyOnWriteArrayList<MedicalPlan>();
        departments.add(new Department(Department.DepartmentName.Cardiology));
        departments.add(new Department(Department.DepartmentName.Orthopedy));
        departments.add(new Department(Department.DepartmentName.Virology));
        medicalRecords = new File("medicalRecords.txt");
        try {
            medicalRecords.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileOutputStream write = new FileOutputStream(medicalRecords)) {

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(write));
            bw.write("First name - Family name - diagnosis - days hospitalised");
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashSet<Doctor> getDoctors() {
        return doctors;
    }

    public HashSet<Nurse> getNurses() {
        return nurses;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public CopyOnWriteArrayList<MedicalPlan> getMedicalPlans() {
        return medicalPlans;
    }

    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }

    public void addNurse(Nurse nurse){
        nurses.add(nurse);
    }

    public void hospitalisePatient(Patient patient){
        Thread waiting = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean available = false;
                Doctor doctor = null;
                Random rand = new Random();
                ArrayList<Doctor> docs = new ArrayList<Doctor>();
                docs.addAll(doctors);
                while (!available){
                    Doctor d = docs.get(rand.nextInt(docs.size()));
                    if (d.isAvailable()){
                        doctor = d;
                            break;
                    }
                }
                doctor.addPatient(patient);
                patient.setDoctor(doctor);
                MedicalPlan m = doctor.createMedicalPlan(patient);
                medicalPlans.add(m);

                try(FileWriter write = new FileWriter(medicalRecords, true)) {

                    write.write(m.getPatient().getFirstName() + " - " + m.getPatient().getLastName() + " - " + m.getDiagnose() + " - " + m.getDaysOfTreatment() + System.lineSeparator());
                    write.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                switch (m.getDiagnose()){
                    case "Broken bone":
                        departments.get(1).addPatient(patient);
                        patient.setDepartment(departments.get(1));
                        break;
                    case "Heart attack":
                        departments.get(0).addPatient(patient);
                        patient.setDepartment(departments.get(0));
                        break;
                    case "Influenza":
                        departments.get(2).addPatient(patient);
                        patient.setDepartment(departments.get(2));
                        break;
                    default:
                        System.out.println("Can't hospitalise patient.");
                        break;
                }
                System.out.println(patient.getFirstName() + " " + patient.getLastName() + ", " + patient.getGender() +
                " has been hospitalised with " + m.getDiagnose() + ". Doctor: dr. " + doctor.getFirstName() + " " + doctor.getLastName());

            }
        });
        waiting.start();
    }

    public synchronized void startWorDay(){
        for (MedicalPlan m : medicalPlans){
            m.setTakenMeds(false);
        }
        for (Nurse n : nurses){
            n.giveMedications();
        }
        for (Doctor d : doctors){
            d.visitation();
        }
    }

    public synchronized void endOfWorkDay(){
        for (MedicalPlan m : medicalPlans){
            if (m.getDaysLeft() == 0){
                m.getPatient().getDepartment().removePatient(m.getPatient());
                m.getPatient().getDoctor().getPatients().remove(m.getPatient());
                medicalPlans.remove(m);
            }
            m.setDaysLeft(m.getDaysLeft() - 1);
        }

    }
}
