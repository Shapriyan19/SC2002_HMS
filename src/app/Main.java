package app;

import user.DataLoader;
import user.Patient;
import user.Doctor;
import user.Administrator;
import user.Pharmacist;

public class Main {
    public static void main(String[] args) {
        // Load all data (Doctors, Patients, Administrators, Pharmacists) from CSV files and create random appointments
        DataLoader.loadAllData();

        // Optionally, print out the loaded doctors and patients to confirm they are loaded
        // System.out.println("\nLoaded Doctors:");
        // for (Doctor doctor : Doctor.getDoctorsList()) {
        //     System.out.println(doctor.getName() + " (ID: " + doctor.getHospitalID() + ")");
        // }

        // System.out.println("\nLoaded Patients:");
        // for (Patient patient : Patient.getPatientsList()) {
        //     System.out.println(patient.getName() + " (ID: " + patient.getHospitalID() + ")");
        // }

        // System.out.println("\nLoaded Administrators:");
        // for (Administrator administrator : Administrator.getAdministratorsList()) {
        //     System.out.println(administrator.getName() + " (ID: " + administrator.getHospitalID() + ")");
        // }

        // System.out.println("\nLoaded Pharmacists:");
        // for (Pharmacist pharmacist : Pharmacist.getPharmacistsList()) {
        //     System.out.println(pharmacist.getName() + " (ID: " + pharmacist.getHospitalID() + ")");
        // }
    }
}
