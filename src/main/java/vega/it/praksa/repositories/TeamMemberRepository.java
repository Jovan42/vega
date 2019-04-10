package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.TeamMember;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    Optional<TeamMember> getByUsername(String username);

}
