package hospital.servicedoctor.controller;

import hospital.servicedoctor.model.Staff;
import hospital.servicedoctor.service.StaffService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("${vAPI}/staff/doctors")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    /*************************************** UTILITIES HIDDEN METHODS - USING FOR TESTING ********************************************/

    /**
     * Get Pageable staff doctors
     * @return Page of staff doctors
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Staff>> getAllStaffDoctor(Pageable pageable) {
        return ResponseEntity.ok(this.staffService.getAllStaffDoctor(pageable));
    }

    /**
     * Get all staff doctors
     * @return List of staff doctors
     */
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Staff>> getAllStaffDoctorList() {
        return ResponseEntity.ok(this.staffService.getAllStaffDoctorList());
    }

    /**
     * Create a new staff doctor
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Staff> createStaffDoctor(@RequestBody Staff staff) {
        return ResponseEntity.ok(this.staffService.createStaffDoctor(staff));
    }

    /**
     * Get staff doctor by id
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Staff> getStaffDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(this.staffService.getStaffDoctorById(id));
    }

    /**
     * Update staff doctor
     */
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Staff> updateStaffDoctor(@PathVariable Long id, @RequestBody Staff staff) {
        return ResponseEntity.ok(this.staffService.updateStaffDoctor(id, staff));
    }

    /**
     * Delete staff doctor
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteStaffDoctor(@PathVariable Long id) {
        this.staffService.deleteStaffDoctor(id);
        return ResponseEntity.noContent().build();
    }



}
