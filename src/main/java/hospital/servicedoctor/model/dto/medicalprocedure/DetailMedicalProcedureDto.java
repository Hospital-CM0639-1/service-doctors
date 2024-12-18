package hospital.servicedoctor.model.dto.medicalprocedure;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DetailMedicalProcedureDto extends MedicalProcedureDto {
    private EmergencyVisitDto emergencyVisit;
    private StaffDto staff;
}

@Getter
@Setter
class StaffDto {
    private Long id;
}

@Getter
@Setter
class EmergencyVisitDto {
    private Long id;
}