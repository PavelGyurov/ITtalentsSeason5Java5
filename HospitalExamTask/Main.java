package com.company;

public class Main {

    public static void main(String[] args){

        Hospital hospital = new Hospital();
        Thread time = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    System.out.println("===== Day " + (i+1) + " =====");
                    hospital.startWorDay();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hospital.endOfWorkDay();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        time.start();

        hospital.addDoctor(new Doctor("Ivan", "Ivanov ", "0888456789", "Cardiology"));
        hospital.addDoctor(new Doctor("Boris", "Zdravkov ", "0895546540", "Endocrinology"));
        hospital.addDoctor(new Doctor("Kiril", "Buchvarov ", "0896546565", "Anaesthetics"));
        hospital.addDoctor(new Doctor("Stanislav", "Aleksandrov ", "0993033226", "Psychiatry"));
        hospital.addDoctor(new Doctor("Plamen", "Dimitrov ", "0975646555", "Surgery"));

        hospital.addNurse(new Nurse("Desislava", "Tomova", "08451239875", 2, hospital.getDepartments().get(0)));
        hospital.addNurse(new Nurse("Yana", "Borislavova", "08415123335", 15, hospital.getDepartments().get(0)));
        hospital.addNurse(new Nurse("Lidia", "Martinova", "08235613262", 12, hospital.getDepartments().get(1)));
        hospital.addNurse(new Nurse("Mila", "Markova", "08945126456", 20, hospital.getDepartments().get(1)));
        hospital.addNurse(new Nurse("Eleonora", "Daskalova", "09845131512", 8, hospital.getDepartments().get(2)));
        hospital.addNurse(new Nurse("Zornitsa", "Minkova", "09841561615", 25, hospital.getDepartments().get(2)));

        hospital.hospitalisePatient(new Patient("Ivan", "Dimitrov", "0912355366", Patient.Gender.Male, 25));
        hospital.hospitalisePatient(new Patient("Silva", "Antonova", "0861218465", Patient.Gender.Female, 45));
        hospital.hospitalisePatient(new Patient("Apostol", "Dragomirov", "0856612345", Patient.Gender.Male, 32));
        hospital.hospitalisePatient(new Patient("Eleonora", " Danielova", "0892313455", Patient.Gender.Female, 58));
        hospital.hospitalisePatient(new Patient("Marta", "Genkova", "0951324545", Patient.Gender.Female, 65));

        for (Nurse n : hospital.getNurses()){
            n.giveMedications();
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hospital.hospitalisePatient(new Patient("Ivanka", "Dimitrova", "0912355366", Patient.Gender.Female, 35));
    }
}
