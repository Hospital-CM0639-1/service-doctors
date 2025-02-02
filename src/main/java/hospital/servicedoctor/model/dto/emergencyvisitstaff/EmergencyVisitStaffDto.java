package hospital.servicedoctor.model.dto.emergencyvisitstaff;

import hospital.servicedoctor.model.enums.EStaffRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmergencyVisitStaffDto {

    private EStaffRole staffRole;
    private Long visitId;
    private Long staffId;
    private LocalDateTime assignedAt;
    private EmergencyVisitDto emergencyVisit;

}

@Getter
@Setter
class EmergencyVisitDto {
    private Long id;
    private String triageNotes;
    private String priorityLevel;
    private PatientDto patient;
}

@Getter
@Setter
class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
}
