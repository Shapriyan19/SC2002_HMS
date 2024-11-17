# SC2002_HMS
# Hospital Management System (HMS)

## Overview
The Hospital Management System (HMS) is a Java-based application designed to automate and manage key hospital operations. The system enhances the efficiency of hospital resource management, improves patient care, and streamlines administrative processes. It includes features such as patient management, appointment scheduling, staff management, and medication inventory management.

## Features

### General Features:
#### Role-Based Access:
- Users log in with their unique hospital ID and a default password ("password").
- Upon first login, users can change their password.
- The system validates login credentials and grants role-specific access.

#### User Roles:
- **Patient**
- **Doctor**
- **Pharmacist**
- **Administrator**

### Role-Specific Features:

#### Patient:
- **Information Access**: 
  - View medical records (Patient ID, Name, Date of Birth, Gender, Contact Information, Blood Type, Past Diagnoses, and Treatments).
  - Update non-medical personal information (e.g., contact number, email).
  - Restrictions: Cannot modify past diagnoses, treatments, or blood type.
  
- **Appointment Management**:
  - View available appointment slots with doctors.
  - Schedule, reschedule, or cancel appointments.
  - View the status of scheduled appointments (e.g., confirmed, canceled, completed).
  - Access Appointment Outcome Records for past appointments.

#### Doctor:
- **Medical Record Management**:
  - View and update medical records of patients under their care.
  - Add new diagnoses, prescriptions, and treatment plans.

- **Appointment Management**:
  - View personal schedule and set availability.
  - Accept or decline appointment requests.
  - Record appointment outcomes (Date, Type of service, Prescribed medications, Consultation notes).

#### Pharmacist:
- **Prescription Management**:
  - View and update the status of prescriptions in Appointment Outcome Records.
  - Monitor and manage medication inventory (stock levels).
  - Submit replenishment requests for low-stock medications.

#### Administrator:
- **Staff Management**:
  - Add, update, or remove hospital staff (doctors, pharmacists).
  - Display staff list filtered by role, gender, or age.

- **Appointment Management**:
  - Access real-time updates of appointments (Patient ID, Doctor ID, Appointment status).
  - Access Appointment Outcome Records for completed appointments.

- **Inventory Management**:
  - Manage medication inventory (add, remove, update stock levels).
  - Update low stock level alerts.
  - Approve replenishment requests and automatically update stock levels.

- **System Initialization**:
  - Import initial staff list, patient list, and inventory details from files.

## Technologies Used
- **Java**: Core programming language.
- **Object-Oriented Programming (OOP)**: Encapsulation, inheritance, and polymorphism.
- **File Handling**: Persistent storage for staff, patient, appointment, and inventory data.
- **Command-Line Interface (CLI)**: Interactive menus for each user role.

## Installation

### Prerequisites:
- Java Development Kit (JDK 17 or later).
- Git (for cloning the repository).

### Steps to Install:
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/username/hospital-management-system.git
   cd hospital-management-system

2. **Compile the Code**:
  ```bash
  javac -d out src/hmsApp/*.java src/user/*.java src/appointment/*.java src/inventory/*.java

3. **Run the application**:
  ```bash
  java -cp out hmsApp.LoginFunctionality
Verify Data Files: Ensure the following files are present in the data/ directory:

staff.csv (initial staff list)
patients.csv (initial patient data)
medicine.csv (initial medication inventory)
Run the Program: Run the LoginFunctionality class to start the application.

Usage
Login:

Use your hospital ID and password to log in.
Access role-specific functionalities based on your role.
Role-Specific Actions:

Manage appointments, inventory, or patient records as per your role (details under Features).
Data Files
staff.csv: Contains hospital staff details (role, name, age, gender).
patients.csv: Contains patient information (medical records, contact info).
medicine.csv: Tracks medication details, including stock levels and low-stock alerts.
Authors
Kalaiselvan Shanmugapriyan
Manikandan Yuvana
Rajadharshini Nedumaran
Murugappan Venkatesh
Srishakti Nedunchelian
License
This project is licensed under the MIT License.

Acknowledgments
SC2002 Instructors and TAs for their guidance.
The team members for their contributions.
