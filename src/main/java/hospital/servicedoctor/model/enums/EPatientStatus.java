package hospital.servicedoctor.model.enums;

public enum EPatientStatus {
    WAITING("waiting"),
    IN_TREATMENT("in_treatment"),
    DISCHARCHED("discharged"),
    ADMITTED("admitted");

    private final String status;

    EPatientStatus (String status) {
        this.status = status.toLowerCase();
    }

    public String getStatus() {
        return status;
    }
}
