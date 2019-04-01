package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.TeamMember;

public interface TeamMemberRepositry extends JpaRepository<TeamMember, Long> {
}
