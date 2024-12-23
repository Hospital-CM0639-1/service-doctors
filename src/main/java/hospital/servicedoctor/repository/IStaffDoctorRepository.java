package hospital.servicedoctor.repository;

import hospital.servicedoctor.model.Staff;
import hospital.servicedoctor.model.enums.EStaffRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStaffDoctorRepository extends JpaRepository<Staff, Long> {

    Page<Staff> findAllByRole(EStaffRole role, Pageable pageable);
    List<Staff> findAllByRole(EStaffRole role);

    Optional<Staff> findByIdAndRole(Long id, EStaffRole role);

}
