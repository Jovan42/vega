package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
