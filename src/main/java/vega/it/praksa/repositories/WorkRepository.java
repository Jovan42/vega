package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vega.it.praksa.model.Work;

import java.util.Date;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    //TODO
    //List<Work> findAllByDate_Day(Date date);

}
