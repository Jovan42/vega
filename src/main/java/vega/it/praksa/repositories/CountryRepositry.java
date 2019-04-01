package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Country;

public interface CountryRepositry extends JpaRepository<Country, Long> {
}
