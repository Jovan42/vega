package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.ProjectMember;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> getByUsername(String username);
    List<Employee> findAllByProjectMember_IdIsNull();

}
