package user;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
// import java.nio.file.*;
import medical.MedicalRecord;
import medical.Diagnosis;
import medical.LabTest;
import medical.Prescription;
import medical.Treatment;

public class Doctor extends User {

    private static List<Doctor> doctorsList=new ArrayList<>();
    // Instance variables
    // private String doctorID; to be removed
    private String name;
    // private String specialisation;
    // private List<String> schedule;
    // private List<Patient> patientList;
    private String gender;
    private int age;
    
    // Constructor
    public Doctor(Role role, String name, String gender, int age) {
        super(generateNewHospitalID(role), role);
        // this.doctorID = doctorID;    to be removed
        this.name = name;
        // this.specialisation = specialisation;
        // this.schedule = new ArrayList<>();
        // this.patientList = new ArrayList<>();
        this.gender = gender;
        this.age = age;
        doctorsList.add(this);
        updateCSV();
    }

    //Constructor to get from CSV Files
    public Doctor(String hospitalID,String name,String password,Role role,String gender,int age){
        super(hospitalID, role,password);
        this.name=name;
        this.gender=gender;
        this.age=age;
        doctorsList.add(this);
    }

    // public void addPatient(Patient patient) {
    //     patientList.add(patient);
    //     System.out.println("Added patient with ID: " + patient.getHospitalID());
    // }

    // private Patient getPatientById(String patientID) {
    //     for (Patient patient : patientList) {
    //         if (patient.getHospitalID().equals(patientID)) {
    //             return patient;
    //         }
    //     }
    //     System.out.println("Patient with ID " + patientID + " not found.");
    //     return null;
    // }

    // private MedicalRecord getMedicalRecordByPatient(Patient patient) {
    //     if (patient != null) {
    //         return patient.getMedicalRecord();
    //     }
    //     System.out.println("No medical record found for the provided patient.");
    //     return null;
    // }

    // public void viewPatientRecord(String patientID) {
    //     Patient patient = getPatientById(patientID);
    //     if (patient == null) return;

    //     MedicalRecord medicalRecord = getMedicalRecordByPatient(patient);
    //     System.out.println("---- Medical Record for Patient ----");
    //     System.out.println("Patient ID: " + patientID);
    //     System.out.println("Name: " + patient.getName());
    //     System.out.println("Date of Birth: " + patient.getDateOfBirth());
    //     System.out.println("Gender: " + patient.getGender());
    //     System.out.println("Blood Type: " + patient.getBloodType());
    //     System.out.println("Contact Information:");
    //     System.out.println("    Phone Number: " + patient.getPhoneNumber());
    //     System.out.println("    Email: " + patient.getEmail());

    //     // Display Diagnoses
    //     System.out.println("\nDiagnoses:");
    //     for (Diagnosis diagnosis : medicalRecord.getDiagnoses()) {
    //         System.out.println(diagnosis);
    //     }

    //     // Display Lab Tests
    //     System.out.println("\nLab Tests:");
    //     for (LabTest labTest : medicalRecord.getLabTests()) {
    //         System.out.println(labTest);
    //     }

    //     // Display Treatments
    //     System.out.println("\nTreatments:");
    //     for (Treatment treatment : medicalRecord.getTreatments()) {
    //         System.out.println(treatment);
    //     }

    //     // Display Prescriptions
    //     System.out.println("\nPrescriptions:");
    //     for (Prescription prescription : medicalRecord.getPrescriptions()) {
    //         System.out.println(prescription);
    //     }
    //     System.out.println("---- End of Medical Record ----");
    // }

    // public void updatePatientRecord(MedicalRecord medicalRecord, Diagnosis newDiagnosis, Treatment newTreatment, Prescription newPrescription) {
    //     System.out.println("Updating medical record for patient ID: " + medicalRecord.getPatientID());

    //     if (newDiagnosis != null) {
    //         medicalRecord.addDiagnosis(newDiagnosis);
    //         System.out.println("Added diagnosis: " + newDiagnosis);
    //     }

    //     if (newTreatment != null) {
    //         medicalRecord.addTreatment(newTreatment);
    //         System.out.println("Added treatment plan: " + newTreatment);
    //     }

    //     if (newPrescription != null) {
    //         medicalRecord.addPrescription(newPrescription);
    //         System.out.println("Added prescription: " + newPrescription);
    //     }
    //     System.out.println("Medical record updated successfully.");
    // }

    // public void viewPersonalSchedule() {
    //     System.out.println("Doctor's Schedule:");
    //     for (String slot : schedule) {
    //         System.out.println(slot);
    //     }
    // }

    // public void setAvailabilityForAppointment(String timeSlot) {
    //     schedule.add(timeSlot);
    //     System.out.println("Availability set for: " + timeSlot);
    // }

    // public void acceptAppointment(String appointmentID) {
    //     System.out.println("Accepted appointment with ID: " + appointmentID);
    // }

    // public void declineAppointment(String appointmentID) {
    //     System.out.println("Declined appointment with ID: " + appointmentID);
    // }

    // public void viewUpcomingAppointments() {
    //     System.out.println("Upcoming Appointments:");
    // }

    // public void recordAppointmentOutcome(String appointmentID, String date, String serviceType, String medicationName, String medicationStatus, String consultationNotes) {
    //     System.out.println("Recording outcome for appointment ID: " + appointmentID);
    //     System.out.println("Date: " + date);
    //     System.out.println("Service Type: " + serviceType);
    //     System.out.println("Prescribed Medication: " + medicationName + " (Status: " + medicationStatus + ")");
    //     System.out.println("Consultation Notes: " + consultationNotes);
    // }

    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + HospitalID);
            return true;
        } else {
            System.out.println("Login failed: Incorrect password.");
            return false;
        }
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword) {
        if (!isLoggedIn) {
            System.out.println("Change password failed: Doctor is not logged in.");
            return false;
        }

        if (this.getPassword().equals(currentPassword)) {
            if (newPassword.length() >= 8) {
                setPassword(newPassword);
                updateCSV(); // Update CSV when password changes
                System.out.println("Password changed successfully for Doctor: " + name);
                return true;
            } else {
                System.out.println("New password must be at least 8 characters long.");
                return false;
            }
        } else {
            System.out.println("Change password failed: Current password is incorrect.");
            return false;
        }
    }

    @Override
    public void logout() {
        System.out.println("Logging out doctor: " + name);
    }

    public void updateCSV() {
        File file = new File("Data/Staff_List.csv");
        List<String> lines = new ArrayList<>();
        boolean isNew = true;
        boolean isHeaderWritten = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if header is already written
                if (line.startsWith("Staff ID")) {
                    if (!isHeaderWritten) {
                        lines.add(line); // Write header only once
                        isHeaderWritten = true;
                    }
                } else if (line.startsWith(HospitalID + ",")) {
                    // Check if the record exists (by matching HospitalID)
                    isNew = false;
                    lines.add(toCSVFormat()); // Update the existing record
                } else {
                    lines.add(line); // Keep old records intact
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // If the record doesn't exist, add it as a new one
        if (isNew) {
            lines.add(toCSVFormat());
        }
    
        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (!isHeaderWritten) {
                // Write header if it hasn't been written yet
                writer.write("Staff ID,Name,Password,Role,Gender,Age");
                writer.newLine();
            }
    
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
    
            System.out.println("Staff data updated in Staff_List.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toCSVFormat() {
        return HospitalID + "," + name + ","+ password + "," + role + "," + gender + "," + age;
    }

    public static void loadDoctorsFromCSV() {
        File file = new File("Data/Staff_List.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true; // Skip header
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                if (data.length == 6) { // Assuming 6 columns as per your header
                    Role role = Role.valueOf(data[3]); // Assuming Role can be parsed from a String
                    Doctor doctor = new Doctor(role, data[1], data[4], Integer.parseInt(data[5]));
                    doctor.setPassword(data[2]); // Set password separately if needed
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Doctor> getDoctorsList() {
        return doctorsList;
    }
    // Getters and setters
    public String getDoctorID() {
        return HospitalID; 
    }
    
    public String getName() { 
        return name; 
    }
    // public String getSpecialisation() { 
    //     return specialisation; 
    // }
    
    // public List<String> getSchedule() { 
    //     return schedule; 
    // }
    
    // public List<Patient> getPatientList() { 
    //     return patientList; 
    // }
    
    public String getGender() { 
        return gender; 
    }
    
    public int getAge() { 
        return age; 
    }

    // public void addPatient(int patientID) {
    //     patientList.add(patientID);
    //     System.out.println("Added patient with ID: " + patientID);
    // }
}
