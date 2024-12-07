package hospital.servicedoctor.service;

import hospital.servicedoctor.model.StaffDoctor;
import hospital.servicedoctor.model.enums.EStaffRole;
import hospital.servicedoctor.repository.IStaffDoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffDoctorService {

    private static final Logger logger = LoggerFactory.getLogger(StaffDoctorService.class);

    private final IStaffDoctorRepository staffRepository;

    public StaffDoctorService(IStaffDoctorRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /**
     * Get all staff doctors
     * @return List of staff doctors
     */
    public List<StaffDoctor> getAllStaffDoctor() {
        return staffRepository.findAllByRole(EStaffRole.DOCTOR);
    }

}
