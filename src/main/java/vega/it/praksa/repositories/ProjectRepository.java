package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Client;
import vega.it.praksa.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByNameContaining(String name);
    List<Project> findAllByNameStartsWithOrNameStartsWith(String upperLetter, String lowerLetter);
    List<Project> findAllByClient(Client client);
}
