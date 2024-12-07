package hospital.servicedoctor.controller;

import hospital.servicedoctor.model.StaffDoctor;
import hospital.servicedoctor.service.StaffDoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff/doctor")
public class StaffDoctorController {

    private final StaffDoctorService staffDoctorService;

    public StaffDoctorController(StaffDoctorService staffDoctorService) {
        this.staffDoctorService = staffDoctorService;
    }

    /**
     * Get all staff
     * @return List of staff
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StaffDoctor>> getAllStaff() {
        return ResponseEntity.ok(this.staffDoctorService.getAllStaffDoctor());
    }

}
