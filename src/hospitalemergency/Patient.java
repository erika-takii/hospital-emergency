package hospitalemergency;

public class Patient {
    private String name;
    private int severityLevel;
    private boolean isTransported;

    public Patient(String name, int severityLevel) {
        this.name = name;
        this.severityLevel = severityLevel;
        this.isTransported = false;
    }

    public String getName() {
        return name;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public boolean isTransported() {
        return isTransported;
    }

    public void setIsTransported(boolean transported) {
        this.isTransported = transported;
    }

    public void reduceSeverityLevel(int amount) {
        severityLevel = Math.max(0, severityLevel - amount);
    }

    public boolean isRecovered() {
        return severityLevel <= 0;
    }

    @Override
    public String toString() {
        return name + " (Severity: " + severityLevel + ")";
    }
}
