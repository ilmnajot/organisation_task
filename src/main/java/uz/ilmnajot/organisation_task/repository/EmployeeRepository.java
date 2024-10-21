package uz.ilmnajot.organisation_task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.organisation_task.entity.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByPinfl(String pinfl);

    Optional<Employee> findByPinflOrId(String pinfl, Long employeeId);

    Page<Employee> findAllByDeletedFalse(Pageable pageable);
    Page<Employee> findAllByDeletedTrue(Pageable pageable);


}
