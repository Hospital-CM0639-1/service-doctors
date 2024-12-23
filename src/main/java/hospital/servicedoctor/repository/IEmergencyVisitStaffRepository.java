package hospital.servicedoctor.repository;

import hospital.servicedoctor.model.EmergencyVisitStaff;
import hospital.servicedoctor.model.EmergencyVisitStaffId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmergencyVisitStaffRepository extends JpaRepository<EmergencyVisitStaff, EmergencyVisitStaffId> {
    @Query("SELECT evs FROM EmergencyVisitStaff evs " +
            "JOIN FETCH evs.emergencyVisit ev " +
            "JOIN FETCH ev.patient p " +
            "WHERE evs.staff.id = :doctorId AND evs.staffRole = 'DOCTOR'")
    List<EmergencyVisitStaff> findPatientsAssignedToDoctor(@Param("doctorId") Long doctorId);
}
