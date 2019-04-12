package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {}
