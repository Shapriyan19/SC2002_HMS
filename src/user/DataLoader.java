package user;

import appointment.TimeSlot;
import appointment.Appointment;
import appointment.AppointmentScheduler;
import java.io.*;
import java.util.*;

public class DataLoader {

    // Load all user data from CSV files at startup
    public static void loadAllData() {
        loadDoctors();
        loadAdministrators();
        loadPharmacists();
        loadPatients();
        createRandomAppointments();  // New method to schedule random appointments after loading data
    }

    // Load doctors from CSV and store in Doctor's static list
    private static void loadDoctors() {
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
                if (data.length == 6) { // Assuming 6 columns: Staff ID, Name, Password, Role, Gender, Age
                    Role role = Role.valueOf(data[3]);
                    if (role == Role.DOCTOR) {
                        Doctor doctor = new Doctor(data[0], data[1], data[2], role, data[4], Integer.parseInt(data[5]));
                        Doctor.getDoctorsList().add(doctor);
                        
                        // Print confirmation that a doctor has been loaded
                        System.out.println("Loaded Doctor: " + doctor.getName() + ", ID: " + doctor.getHospitalID());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load patients from CSV and store in Patient's static list
    public static void loadPatients() {
        File file = new File("Data/Patient_List.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true; // Skip header
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                if (data.length == 8) { // Assuming 8 columns: Patient ID, Name, Password, Date of Birth, Gender, Blood Type, Phone Number, Email
                    String hospitalID = data[0];
                    String name = data[1];
                    String password = data[2];
                    String dateOfBirth = data[3];
                    String gender = data[4];
                    String bloodType = data[5];
                    long phoneNumber = Long.parseLong(data[6]);
                    String email = data[7];
                    
                    Role role = Role.PATIENT; // Assuming role is always 'PATIENT' for this class
    
                    // Create a new Patient object from CSV data and add to patientsList
                    Patient patient = new Patient(hospitalID, name, password, dateOfBirth, gender, bloodType, phoneNumber, email, role);
                    Patient.getPatientsList().add(patient);

                    // Print confirmation that a patient has been loaded
                    System.out.println("Loaded Patient: " + patient.getName() + ", ID: " + patient.getHospitalID());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //New method to create random appointments
    private static void createRandomAppointments() {
        Random random = new Random();

        List<Patient> patients = Patient.getPatientsList();
        List<Doctor> doctors = Doctor.getDoctorsList();

        // Randomly assign appointments for patients with doctors
        for (Patient patient : patients) {
            Doctor doctor = doctors.get(random.nextInt(doctors.size()));  // Random doctor from the list

            // Randomly choose a date in November
            String date = "2024-11-" + (random.nextInt(30) + 1);

            // Randomly choose a time slot
            String startTime = "10:00 AM";
            String endTime = "10:30 AM";  // Example of a 30-minute appointment slot
            TimeSlot timeSlot = new TimeSlot(startTime, endTime);

            // Create an Appointment and AppointmentScheduler
            Appointment appointment = new Appointment(patient, doctor, date, timeSlot);
            AppointmentScheduler scheduler = new AppointmentScheduler(appointment);

            // Try to schedule the appointment
            System.out.println("Scheduling appointment for " + patient.getName() + " with Dr. " + doctor.getName() + " on " + date);
            scheduler.scheduleAppointment();  // This will schedule the appointment and print whether it was successful or not
        }
    }

    // Load administrators from CSV and store in Administrator's static list (implement similarly)
    private static void loadAdministrators() {
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
                if (data.length == 6) { // Assuming 6 columns: Staff ID, Name, Password, Role, Gender, Age
                    Role role = Role.valueOf(data[3]);
                    if (role == Role.ADMINISTRATOR) {
                        Administrator administrator = new Administrator(data[0], data[1], data[2], role, data[4], Integer.parseInt(data[5]));
                        Administrator.getAdministratorsList().add(administrator);
                        
                        // Print confirmation that an administrator has been loaded
                        System.out.println("Loaded Administrator: " + administrator.getName() + ", ID: " + administrator.getHospitalID());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load pharmacists from CSV and store in Pharmacist's static list (implement similarly)
    private static void loadPharmacists() {
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
                if (data.length == 6) { // Assuming 6 columns: Staff ID, Name, Password, Role, Gender, Age
                    Role role = Role.valueOf(data[3]);
                    if (role == Role.PHARMACIST) {
                        Pharmacist pharmacist = new Pharmacist(data[0], data[1], data[2], role, data[4], Integer.parseInt(data[5]));
                        Pharmacist.getPharmacistsList().add(pharmacist);
                        
                        // Print confirmation that a pharmacist has been loaded
                        System.out.println("Loaded Pharmacist: " + pharmacist.getName() + ", ID: " + pharmacist.getHospitalID());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}
