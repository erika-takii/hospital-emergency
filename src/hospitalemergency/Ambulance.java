package hospitalemergency;

public class Ambulance {
    private String id;
    private boolean available;
    private Hospital destinationHospital;

    public Ambulance(String id) {
        this.id = id;
        this.available = true;
        this.destinationHospital = null;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDestinationHospital(Hospital hospital) {
        this.destinationHospital = hospital;
    }

    public void transportPatient(Patient patient) {
        System.out.println("Transporting " + patient.getName() + " to " + destinationHospital.getName() + "...");
        setAvailable(false);
    }

    @Override
    public String toString() {
        return id + " (Available: " + available + ")";
    }
}
