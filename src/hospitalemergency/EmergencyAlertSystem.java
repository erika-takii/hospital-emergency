package hospitalemergency;

import java.io.*;
import java.util.*;

public class EmergencyAlertSystem {
    private List<Hospital> hospitals;
    private List<Ambulance> ambulances;
    private List<Patient> patients;

    public EmergencyAlertSystem() {
        this.hospitals = new ArrayList<>();
        this.ambulances = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public List<Hospital> getHospitals() { return hospitals; }
    public List<Ambulance> getAmbulances() { return ambulances; }
    public List<Patient> getPatients() { return patients; }

    public void initialize(String hospitalsFile, String ambulancesFile, String doctorsFile, String patientsFile) {
        readHospitals(hospitalsFile);
        readAmbulances(ambulancesFile);
        readDoctors(doctorsFile);
        readPatients(patientsFile);

        System.out.println("===== Data Loaded =====");
        System.out.println(hospitals);
        System.out.println(ambulances);
        for (Hospital h : hospitals) {
            System.out.println(h.getName() + ": " + h.getDoctors());
        }
        System.out.println(patients);
    }

    private void readHospitals(String fileName) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                int capacity = scanner.nextInt();
                hospitals.add(new Hospital(name, capacity));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading hospitals file: " + e.getMessage());
        }
    }

    private void readDoctors(String fileName) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                int efficiency = scanner.nextInt();
                Doctor doctor = new Doctor(name, efficiency);
                for (Hospital h : hospitals) {
                    h.registerDoctor(doctor);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading doctors file: " + e.getMessage());
        }
    }

    private void readAmbulances(String fileName) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNext()) {
                String id = scanner.next();
                ambulances.add(new Ambulance(id));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading ambulances file: " + e.getMessage());
        }
    }

    private void readPatients(String fileName) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                int severityLevel = scanner.nextInt();
                patients.add(new Patient(name, severityLevel));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading patients file: " + e.getMessage());
        }
    }

    public Hospital findAvailableHospital() {
        for (Hospital h : hospitals) {
            if (h.hasSpace()) {
                return h;
            }
        }
        return null;
    }

    public Ambulance findAvailableAmbulance() {
        for (Ambulance a : ambulances) {
            if (a.isAvailable()) {
                return a;
            }
        }
        return null;
    }

    public boolean transportPatient(Patient p) {
        Hospital h = findAvailableHospital();
        Ambulance a = findAvailableAmbulance();
        if (h != null && a != null) {
            a.setDestinationHospital(h);
            a.transportPatient(p);
            h.registerPatient(p);
            System.out.println(p.getName() + " arrived at " + h.getName() + " using " + a.getId());
            p.setIsTransported(true);
            return true;
        }
        if (h == null) {
            System.out.println("No available hospitals.");
        } else {
            System.out.println("No available ambulances.");
        }
        return false;
    }

    public void transportAllPatients() {
        for (Patient p : patients) {
            if (!p.isTransported()) {
                boolean transported = transportPatient(p);
                if (!transported) {
                    System.out.println("Could not transport " + p.getName() + ". Waiting...");
                }
            }
        }
    }

    public void startAllTreatments() {
        for (Hospital h : hospitals) {
            h.treatPatients();
        }
        for (Ambulance a : ambulances) {
            a.setAvailable(true);
            System.out.println("Ambulance " + a.getId() + " is now available.");
        }
    }

    public boolean allPatientsIsRecovered() {
        for (Patient p : patients) {
            if (!p.isRecovered()) {
                return false;
            }
        }
        return true;
    }
}
