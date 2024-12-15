package hospital.servicedoctor.controller;

import hospital.servicedoctor.model.MedicalProcedure;
import hospital.servicedoctor.model.Staff;
import hospital.servicedoctor.service.MedicalProcedureService;
import hospital.servicedoctor.service.StaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-procedures")
public class MedicalProcedureController {

    private final MedicalProcedureService medicalProcedureService;

    public MedicalProcedureController(MedicalProcedureService medicalProcedureService) {
        this.medicalProcedureService = medicalProcedureService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MedicalProcedure>> getAllMedicalProcedures(Pageable pageable) {
        return ResponseEntity.ok(this.medicalProcedureService.getAllPageableMedicalProcedures(pageable));
    }

    @GetMapping(produces = "application/json", value = "doctor/{doctorId}")
    public ResponseEntity<List<MedicalProcedure>> getAllMedicalDoctorProcedures(@PathVariable Long doctorId) {
        return ResponseEntity.ok(this.medicalProcedureService.getAllMedicalDoctorProcedures(doctorId));
    }




}
