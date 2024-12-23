package hospital.servicedoctor.controller;

import hospital.servicedoctor.model.dto.medicalprocedure.DetailMedicalProcedureDto;
import hospital.servicedoctor.model.dto.medicalprocedure.MedicalProcedureDto;
import hospital.servicedoctor.service.MedicalProcedureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("${vAPI}/medical-procedures")
public class MedicalProcedureController {

    private final MedicalProcedureService medicalProcedureService;

    public MedicalProcedureController(MedicalProcedureService medicalProcedureService) {
        this.medicalProcedureService = medicalProcedureService;
    }

    /**
     * Get all medical procedures
     * @param pageable
     * @return Page<MedicalProcedure>
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MedicalProcedureDto>> getAllMedicalProcedures(Pageable pageable) {
        return ResponseEntity.ok(this.medicalProcedureService.getAllPageableMedicalProcedures(pageable));
    }

    /**
     * Get all medical procedures for a doctor  with a given id (WHAT HAS DONE A DOCTOR)
     * @param doctorId
     * @param startDate
     * @param finishDate
     * @return List<MedicalProcedureDto>
     */
    @GetMapping(produces = "application/json", value = "doctor/{doctorId}")
    public ResponseEntity<List<DetailMedicalProcedureDto>> getAllMedicalDoctorProcedures(
            @PathVariable Long doctorId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime startDate,
            @RequestParam(value = "finishDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  LocalDateTime finishDate) {
        return ResponseEntity.ok(this.medicalProcedureService.getAllMedicalDoctorProcedures(doctorId, startDate, finishDate));
    }

    /**
     * Get patient's medical procedures
     * @param patientId
     * @param startDate
     * @param finishDate
     *  @return List<MedicalProcedureDto>
     */
    @GetMapping(produces = "application/json", value = "patient/{patientId}")
    public ResponseEntity<List<DetailMedicalProcedureDto>> getPatientMedicalProcedures(
            @PathVariable Long patientId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime startDate,
            @RequestParam(value = "finishDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime finishDate) {
        return ResponseEntity.ok(this.medicalProcedureService.getPatientMedicalProcedures(patientId, startDate, finishDate));
    }

    /**
     * Save medical procedure
     * @param medicalProcedureDto
     * @return MedicalProcedureDto
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<DetailMedicalProcedureDto> saveMedicalProcedure(@RequestBody DetailMedicalProcedureDto medicalProcedureDto) {
        return ResponseEntity.ok(this.medicalProcedureService.saveMedicalProcedure(medicalProcedureDto));
    }

    /**
     * Update medical procedure
     * @param medicalProcedureDto
     * @param id
     * @return MedicalProcedureDto
     */
    @PutMapping(produces = "application/json", value = "{id}")
    public ResponseEntity<DetailMedicalProcedureDto> updateMedicalProcedure(@RequestBody DetailMedicalProcedureDto medicalProcedureDto,
                                                                            @PathVariable Long id) {
        return ResponseEntity.ok(this.medicalProcedureService.updateMedicalProcedure(medicalProcedureDto, id));
    }

    /**
     * Delete medical procedure
     * @param id
     * @return Boolean
     */
    @DeleteMapping(produces = "application/json", value = "{id}")
    public ResponseEntity<Boolean> deleteMedicalProcedure(@PathVariable Long id) {
        this.medicalProcedureService.deleteMedicalProcedure(id);
        return ResponseEntity.ok(true);
    }

}
