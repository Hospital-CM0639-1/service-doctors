package hospital.servicedoctor.repository;

import hospital.servicedoctor.model.Patient;
import hospital.servicedoctor.model.dto.patientemergency.PatientEmergencyInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long>  {

    @Query("SELECT new hospital.servicedoctor.model.dto.patientemergency.PatientEmergencyInfoDto(" +
            "p.id, p.firstName, p.lastName, " +
            "ev.triageNotes, ev.priorityLevel, ev.admissionTimestamp, ev.patientStatus) " +
            "FROM Patient p " +
            "LEFT JOIN p.emergencyVisits ev " +
            "WHERE p.id = :patientId")
    PatientEmergencyInfoDto findPatientEmergencyInfo(@Param("patientId") Long patientId);
}
