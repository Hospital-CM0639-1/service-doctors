package hospital.servicedoctor.model.dto.patientemergency;

import hospital.servicedoctor.model.enums.EPatientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PatientEmergencyInfoDto {
    private Long patientId;
    private String firstName;
    private String lastName;
    private String triageNotes;
    private String priorityLevel;
    private LocalDateTime admissionTimestamp;
    private EPatientStatus patientStatus;
}
