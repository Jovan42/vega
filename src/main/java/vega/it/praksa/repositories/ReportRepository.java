package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vega.it.praksa.model.Category;
import vega.it.praksa.model.Project;
import vega.it.praksa.model.Report;
import vega.it.praksa.model.TeamMember;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    //TODO: Test
    List<Report> findAllByTeamMemberAndProjectAndCategoryAndDateBetween (TeamMember teamMember, Project project,
                                                                         Category category, Date startDate,
                                                                         Date endDate);
}
