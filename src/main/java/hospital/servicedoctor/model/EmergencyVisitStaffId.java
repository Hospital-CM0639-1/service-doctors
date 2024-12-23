package hospital.servicedoctor.model;

import hospital.servicedoctor.model.enums.EStaffRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class EmergencyVisitStaffId implements Serializable {
    private EStaffRole staffRole;
    private Long visitId;
    private Long staffId;
}
