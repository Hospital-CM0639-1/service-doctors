package hospital.servicedoctor.service;

import hospital.servicedoctor.model.MedicalProcedure;
import hospital.servicedoctor.model.enums.EStaffRole;
import hospital.servicedoctor.repository.IMedicalProcedureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MedicalProcedureService {
    private static final Logger logger = LoggerFactory.getLogger(MedicalProcedureService.class);

    private final IMedicalProcedureRepository medicalProcedureRepository;

    public MedicalProcedureService(IMedicalProcedureRepository medicalProcedureRepository) {
        this.medicalProcedureRepository = medicalProcedureRepository;
    }

    /**
     * Get all pageable medical procedures
     */
    public Page<MedicalProcedure> getAllPageableMedicalProcedures(Pageable pageable) {
        return medicalProcedureRepository.findAll(pageable);
    }

    public List<MedicalProcedure> getAllMedicalDoctorProcedures(Long doctorId) {
        return medicalProcedureRepository.findAllByStaff_RoleAndId(EStaffRole.DOCTOR, doctorId);
    }


}
