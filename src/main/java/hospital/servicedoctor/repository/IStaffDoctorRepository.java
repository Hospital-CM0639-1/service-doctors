package hospital.servicedoctor.repository;

import hospital.servicedoctor.model.StaffDoctor;
import hospital.servicedoctor.model.enums.EStaffRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStaffDoctorRepository extends JpaRepository<StaffDoctor, Long> {

    Page<StaffDoctor> findAllByRole(EStaffRole role, Pageable pageable);
    List<StaffDoctor> findAllByRole(EStaffRole role);

    Optional<StaffDoctor> findByIdAndRole(Long id, EStaffRole role);

}
