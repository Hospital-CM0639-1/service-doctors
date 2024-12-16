package hospital.servicedoctor.repository;

import hospital.servicedoctor.model.MedicalProcedure;
import hospital.servicedoctor.model.enums.EStaffRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IMedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long>  {

    List<MedicalProcedure> findAllByStaff_IdAndStaff_RoleAndProcedureTimestampBetween(Long id, EStaffRole role, LocalDateTime startDate, LocalDateTime finishDate);


}
