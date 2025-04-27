# Hospital Emergency Management System

This project is a simple **Hospital Emergency Management System** built with Java.

It manages:
- Hospitals 
- Ambulances 
- Doctors 
- Patients 
and simulates how emergency patients are transported and treated.

---

## Features

- **Transport patients** to available hospitals using available ambulances.
- **Doctors treat** patients, reducing their severity levels.
- **Automatic recovery** when severity reaches 0.
- **Simulation** of hospital emergency operations.

---

## Technologies

- Java 17
- JUnit 4 for testing

---

## Project Structure

```bash
hospitalemergency/
├── Ambulance.java
├── Doctor.java
├── EmergencyAlertSystem.java
├── Hospital.java
├── Patient.java
└── test/
    └── EmergencyAlertSystemTest.java
