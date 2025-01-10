package hospital.servicedoctor.controller;

import hospital.servicedoctor.model.dto.patientemergency.PatientEmergencyInfoDto;
import hospital.servicedoctor.repository.IPatientRepository;
import hospital.servicedoctor.service.PatientVitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vAPI}/patients")
public class PatientController {

    private final IPatientRepository patientRepository;

    // constructor
    public PatientController(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // get patient by id and info about emergency visit
    @GetMapping(produces = "application/json", value = "/{patientId}/emergency-info")
    public ResponseEntity<PatientEmergencyInfoDto> getPatientEmergencyInfo(@PathVariable Long patientId) {
        return ResponseEntity.ok(this.patientRepository.findPatientEmergencyInfo(patientId));
    }



}
