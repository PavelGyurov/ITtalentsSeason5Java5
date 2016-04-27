package com.company;

public class Nurse extends Person{

    private int experience;
    private Department department;

    public Nurse(String firstName, String lastName, String phoneNumber, int experience, Department department) {
        super(firstName, lastName, phoneNumber);
        this.experience = experience;
        this.department = department;
    }

    public int getExperience() {
        return experience;
    }


    public Department getDepartment() {
        return department;
    }

    public synchronized void giveMedications(){
        Thread giveMeds = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Patient p : department.getPatients()){
                    if (!p.getMedicalPlan().isTakenMeds()){
                        System.out.println("Nurse " + getFirstName() + " " + getLastName() + " has given patient " +
                                p.getFirstName() + " " + p.getLastName() + " in room number " + p.getRoomNumber() + " in section " + department.getDepartmentName() +
                                " medications: " + p.getMedicalPlan().getMedications());
                        p.getMedicalPlan().setTakenMeds(true);
                    }
                }
            }
        });
        giveMeds.start();
    }
}
