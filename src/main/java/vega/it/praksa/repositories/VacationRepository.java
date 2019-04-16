package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Vacation;

import java.util.Date;
import java.util.List;

public interface VacationRepository  extends JpaRepository<Vacation, Long> {
    List<Vacation> findAllByEmployee_IdAndEndDateGreaterThanEqualAndStartDateIsLessThanEqual(Long employeeId, Date startDate, Date endDate);
}
