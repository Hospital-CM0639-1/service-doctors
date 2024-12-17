package hospital.servicedoctor.model.dto;

import hospital.servicedoctor.model.EmergencyVisit;
import hospital.servicedoctor.model.Staff;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddMedicalProcedureDto {
    private Long id;
    private String procedureName;
    private LocalDateTime procedureTimestamp;
    private String description;
    private Float procedureCost;
    private EmergencyVisit emergencyVisit;
    private Staff staff;

}
