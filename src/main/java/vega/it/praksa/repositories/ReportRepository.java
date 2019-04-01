package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Report;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    //TODO: Test
    List<Report> findAllByTeamMemberAndProjectAndCategoryAndDateLessThanEqualAndDateGreaterThanEqual(Date startDate,
                                                                                                     Date endDate);
}
