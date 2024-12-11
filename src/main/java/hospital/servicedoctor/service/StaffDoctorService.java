package hospital.servicedoctor.service;

import hospital.servicedoctor.model.StaffDoctor;
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
public class StaffDoctorService {

    private static final Logger logger = LoggerFactory.getLogger(StaffDoctorService.class);

    private final IStaffDoctorRepository staffRepository;

    public StaffDoctorService(IStaffDoctorRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /**
     * Page staff doctors
     * @return Page of staff doctors
     */
    public Page<StaffDoctor> getAllStaffDoctor(Pageable pageable) {
        return staffRepository.findAllByRole(EStaffRole.DOCTOR, pageable);
    }

    /**
     * Get all staff doctors
     * @return List of staff doctors
     */
    public List<StaffDoctor> getAllStaffDoctorList() {
        return staffRepository.findAllByRole(EStaffRole.DOCTOR);
    }

    /**
     * Create a new staff doctor
     */
    public StaffDoctor createStaffDoctor(StaffDoctor staffDoctor) {
        staffDoctor.setRole(EStaffRole.DOCTOR);
        return staffRepository.save(staffDoctor);
    }

    /**
     * Get staff doctor by id
     */
    public StaffDoctor getStaffDoctorById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    /**
     * Update staff doctor
     */
    public StaffDoctor updateStaffDoctor(Long id, StaffDoctor staffDoctor) {
        Optional<StaffDoctor> staffDoctorOptional = staffRepository.findById(id);
        if (staffDoctorOptional.isEmpty()) {
            return null;
        }
        if (staffDoctorOptional.get().getRole() == EStaffRole.DOCTOR) {
            StaffDoctor staffDoctorUpdate = staffDoctorOptional.get();
            staffDoctorUpdate.setFirstName(staffDoctor.getFirstName());
            staffDoctorUpdate.setLastName(staffDoctor.getLastName());
            staffDoctorUpdate.setEmail(staffDoctor.getEmail());
            staffDoctorUpdate.setPhoneNumber(staffDoctor.getPhoneNumber());
            staffDoctorUpdate.setRole(EStaffRole.DOCTOR);
            staffDoctorUpdate.setDepartment(staffDoctor.getDepartment());
            staffDoctorUpdate.setSpecialization(staffDoctor.getSpecialization());
            staffDoctorUpdate.setHireDate(staffDoctor.getHireDate());
            staffDoctorUpdate.setActive(staffDoctor.isActive());
            return staffRepository.save(staffDoctorUpdate);
        } else {
            throw new IllegalArgumentException("Staff is not a doctor");
        }
    }

    /**
     * Delete staff doctor
     */
    public void deleteStaffDoctor(Long id) {
        Optional<StaffDoctor> staffDoctor = staffRepository.findByIdAndRole(id, EStaffRole.DOCTOR);
        if (staffDoctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }
        staffRepository.deleteById(id);
    }

}
