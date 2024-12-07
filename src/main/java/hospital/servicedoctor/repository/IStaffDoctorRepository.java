package hospital.servicedoctor.repository;

import hospital.servicedoctor.model.StaffDoctor;
import hospital.servicedoctor.model.enums.EStaffRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStaffDoctorRepository extends JpaRepository<StaffDoctor, Long> {

    List<StaffDoctor> findAllByRole(EStaffRole role);

}
