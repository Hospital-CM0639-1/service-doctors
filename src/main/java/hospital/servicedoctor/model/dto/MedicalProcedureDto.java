package hospital.servicedoctor.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import hospital.servicedoctor.model.EmergencyVisit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicalProcedureDto {
    private Long id;
    private String procedureName;
    private LocalDateTime procedureTimestamp;
    private String description;
    private Float procedureCost;

}
