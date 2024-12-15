package hospital.servicedoctor.service;

import hospital.servicedoctor.model.Staff;
import hospital.servicedoctor.model.enums.EStaffRole;
import hospital.servicedoctor.repository.IStaffDoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    private final IStaffDoctorRepository staffRepository;

    public StaffService(IStaffDoctorRepository staffRepository) {
        this.staffRepository = staffRepository;
    }




    /*********************** UTILITIES HIDDEN METHODS ***********************/
    /**
     * Page staff doctors
     * @return Page of staff doctors
     */
    public Page<Staff> getAllStaffDoctor(Pageable pageable) {
        return staffRepository.findAllByRole(EStaffRole.DOCTOR, pageable);
    }

    /**
     * Get all staff doctors
     * @return List of staff doctors
     */
    public List<Staff> getAllStaffDoctorList() {
        return staffRepository.findAllByRole(EStaffRole.DOCTOR);
    }

    /**
     * Create a new staff doctor
     */
    public Staff createStaffDoctor(Staff staff) {
        staff.setRole(EStaffRole.DOCTOR);
        return staffRepository.save(staff);
    }

    /**
     * Get staff doctor by id
     */
    public Staff getStaffDoctorById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    /**
     * Update staff doctor
     */
    public Staff updateStaffDoctor(Long id, Staff staff) {
        Optional<Staff> staffDoctorOptional = staffRepository.findById(id);
        if (staffDoctorOptional.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }
        if (staffDoctorOptional.get().getRole() == EStaffRole.DOCTOR) {
            Staff staffUpdate = staffDoctorOptional.get();
            staffUpdate.setFirstName(staff.getFirstName());
            staffUpdate.setLastName(staff.getLastName());
            staffUpdate.setEmail(staff.getEmail());
            staffUpdate.setPhoneNumber(staff.getPhoneNumber());
            staffUpdate.setRole(EStaffRole.DOCTOR);
            staffUpdate.setDepartment(staff.getDepartment());
            staffUpdate.setSpecialization(staff.getSpecialization());
            staffUpdate.setHireDate(staff.getHireDate());
            staffUpdate.setActive(staff.isActive());
            return staffRepository.save(staffUpdate);
        } else {
            throw new IllegalArgumentException("Staff is not a doctor");
        }
    }

    /**
     * Delete staff doctor
     */
    public void deleteStaffDoctor(Long id) {
        Optional<Staff> staffDoctor = staffRepository.findByIdAndRole(id, EStaffRole.DOCTOR);
        if (staffDoctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }
        staffRepository.deleteById(id);
    }

}
