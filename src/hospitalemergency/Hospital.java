package hospitalemergency;

import java.util.*;

public class Hospital {
    private String name;
    private int capacity;
    private List<Patient> patients;
    private List<Doctor> doctors;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void registerPatient(Patient patient) {
        if (patients.size() < capacity) {
            patients.add(patient);
        } else {
            System.out.println("Hospital " + name + " is full.");
        }
    }

    public void registerDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public boolean hasSpace() {
        return patients.size() < capacity;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void treatPatients() {
        for (Doctor doctor : doctors) {
            for (Patient patient : patients) {
                if (!patient.isRecovered()) {
                    doctor.treat(patient);
                }
            }
        }
    }

    @Override
    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}
