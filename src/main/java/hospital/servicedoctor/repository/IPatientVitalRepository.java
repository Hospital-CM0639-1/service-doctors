package hospital.servicedoctor.repository;

import hospital.servicedoctor.model.PatientVital;
import hospital.servicedoctor.model.enums.EStaffRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface IPatientVitalRepository extends JpaRepository<PatientVital, Long>  {

    List<PatientVital> findAllByStaff_IdAndStaff_RoleAndRecordedAtBetween(Long id, EStaffRole role, LocalDateTime startDate, LocalDateTime finishDate);

    List<PatientVital> findAllByEmergencyVisit_Patient_IdAndRecordedAtBetween(Long patientId, LocalDateTime startDate, LocalDateTime finishDate);

}
