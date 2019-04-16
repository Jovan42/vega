package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByTeamLeader_IdAndEmployeesContains(Long teamLeaderId, Employee employee);
}
