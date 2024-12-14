package hospital.servicedoctor.controller;

import hospital.servicedoctor.model.StaffDoctor;
import hospital.servicedoctor.service.StaffDoctorService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/doctors")
public class StaffDoctorController {

    private final StaffDoctorService staffDoctorService;

    public StaffDoctorController(StaffDoctorService staffDoctorService) {
        this.staffDoctorService = staffDoctorService;
    }

    /**
     * Get Pageable staff doctors
     * @return Page of staff doctors
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<StaffDoctor>> getAllStaff(Pageable pageable) {
        return ResponseEntity.ok(this.staffDoctorService.getAllStaffDoctor(pageable));
    }

    /**
     * Get all staff doctors
     * @return List of staff doctors
     */
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<StaffDoctor>> getAllStaffList() {
        return ResponseEntity.ok(this.staffDoctorService.getAllStaffDoctorList());
    }

    /**
     * Create a new staff doctor
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StaffDoctor> createStaffDoctor(@RequestBody StaffDoctor staffDoctor) {
        return ResponseEntity.ok(this.staffDoctorService.createStaffDoctor(staffDoctor));
    }

    /**
     * Get staff doctor by id
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StaffDoctor> getStaffDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(this.staffDoctorService.getStaffDoctorById(id));
    }

    /**
     * Update staff doctor
     */
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StaffDoctor> updateStaffDoctor(@PathVariable Long id, @RequestBody StaffDoctor staffDoctor) {
        return ResponseEntity.ok(this.staffDoctorService.updateStaffDoctor(id, staffDoctor));
    }

    /**
     * Delete staff doctor
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteStaffDoctor(@PathVariable Long id) {
        this.staffDoctorService.deleteStaffDoctor(id);
        return ResponseEntity.noContent().build();
    }



}
