# Hospital Management System (HMS)

## Overview
The Hospital Management System (HMS) is a Java-based application designed to automate and streamline hospital operations. This system enhances the efficiency of hospital resource management, improves patient care, and simplifies administrative processes through features like patient management, appointment scheduling, staff management, and medication inventory control.

## Key Features

### Authentication System
- Role-based access control
- Secure login with hospital ID
- Password management system
- First-time login password change requirement

### User Roles and Capabilities

#### 🏥 Patient
- View and manage personal medical records
- Schedule and manage appointments
- Update contact information
- Access appointment history and outcomes

#### 👨‍⚕️ Doctor
- Manage patient medical records
- Set availability and manage appointments
- Record diagnoses and treatments
- Create and manage prescriptions

#### 💊 Pharmacist
- Process and update prescriptions
- Manage medication inventory
- Monitor stock levels
- Submit inventory replenishment requests

#### 👤 Administrator
- Comprehensive staff management
- System-wide appointment oversight
- Inventory control and management
- System initialization and data import

## Technical Specifications

### Technology Stack
- Programming Language: Java (JDK 17+)
- Architecture: Object-Oriented Design
- Storage: File-based persistent storage
- Interface: Command-Line Interface (CLI)

### Data Management
The system manages data through CSV files:
- `staff.csv`: Staff records and credentials
- `patients.csv`: Patient information and medical records
- `medicine.csv`: Medication inventory and stock levels

## Installation Guide

### Prerequisites
- Java Development Kit (JDK) 17 or later
- Git version control system
- Terminal or Command Prompt access

### Setup Instructions

1. Clone the repository
```bash
git clone https://github.com/username/hospital-management-system.git
cd hospital-management-system
```

2. Compile the source code
```bash
javac -d out src/hmsApp/*.java src/user/*.java src/appointment/*.java src/inventory/*.java
```

3. Run the application
```bash
java -cp out hmsApp.LoginFunctionality
```

### Data File Setup
Ensure these files are present in the `data/` directory before running:
- `staff.csv` - Initial staff information
- `patients.csv` - Patient database
- `medicine.csv` - Medication inventory data

## System Usage

### Login Process
1. Launch the application
2. Enter your hospital ID
3. Use default password ("password") for first login
4. Change password upon first access

### Role-Specific Operations
Access features based on your assigned role:
- Patients: Appointment booking and medical record access
- Doctors: Patient care and appointment management
- Pharmacists: Medication and prescription handling
- Administrators: System management and oversight

## Development Team

- Kalaiselvan Shanmugapriyan
- Manikandan Yuvana
- Rajadharshini Nedumaran
- Murugappan Venkatesh
- Srishakti Nedunchelian

## License
This project is released under the MIT License.

## Acknowledgments
- SC2002 course instructors and teaching assistants
- All team members for their valuable contributions

---
