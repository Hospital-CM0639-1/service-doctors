package hospital.servicedoctor.controller;

import hospital.servicedoctor.model.EmergencyVisitStaff;
import hospital.servicedoctor.model.dto.emergencyvisitstaff.EmergencyVisitStaffDto;
import hospital.servicedoctor.repository.IEmergencyVisitStaffRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${vAPI}/emergency-visit-staff")
public class EmergencyVisitStaffController {

    private final IEmergencyVisitStaffRepository emergencyVisitStaffRepository;
    private final ModelMapper modelMapper;

    public EmergencyVisitStaffController(IEmergencyVisitStaffRepository emergencyVisitStaffRepository, ModelMapper modelMapper) {
        this.emergencyVisitStaffRepository = emergencyVisitStaffRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Get all patient assigned to a doctor
     * @param doctorId
     * @return List of patient assigned to a doctor
     */
    @GetMapping(value = "doctor/{doctorId}", produces = "application/json")
    public ResponseEntity<List<EmergencyVisitStaffDto>> getPatientsAssignedToDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(this.emergencyVisitStaffRepository.findPatientsAssignedToDoctor(doctorId)
                .stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    // Convert to DTO
    private EmergencyVisitStaffDto convertToDto(EmergencyVisitStaff emergencyVisitStaff) {
        return modelMapper.map(emergencyVisitStaff, EmergencyVisitStaffDto.class);
    }
}
