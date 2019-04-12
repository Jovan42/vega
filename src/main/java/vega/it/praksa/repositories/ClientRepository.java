package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByNameContaining(String name);

    List<Client> findAllByNameStartsWithOrNameStartsWith(String upperLetter, String lowerLetter);
}
