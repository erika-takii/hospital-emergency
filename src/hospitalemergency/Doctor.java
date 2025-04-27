package hospitalemergency;

public class Doctor {
    private String name;
    private int efficiency;

    public Doctor(String name, int efficiency) {
        this.name = name;
        this.efficiency = efficiency;
    }

    public String getName() {
        return name;
    }

    public void treat(Patient patient) {
        patient.reduceSeverityLevel(efficiency);
        System.out.println(name + " treated " + patient.getName() + ". Severity now: " + patient.getSeverityLevel());
    }

    @Override
    public String toString() {
        return name + " (Efficiency: " + efficiency + ")";
    }
}
