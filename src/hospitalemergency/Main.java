package hospitalemergency;

public class Main {
    public static void main(String[] args) {
        EmergencyAlertSystem system = new EmergencyAlertSystem();
        
        system.initialize(
            "resources/hospitals.txt",
            "resources/ambulances.txt",
            "resources/doctors.txt",
            "resources/patients.txt"
        );

        system.transportAllPatients();

        system.startAllTreatments();

        if (system.allPatientsIsRecovered()) {
            System.out.println("All patients have been recovered!");
        } else {
            System.out.println("Some patients are still under treatment.");
        }
    }
}
