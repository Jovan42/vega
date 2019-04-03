package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vega.it.praksa.model.*;

import java.util.Date;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query("select e from Work e where year(e.date) = ?1 and  month(e.date) = ?2 and day(e.date) = ?3")
    List<Work> getForDay(int year, int month, int day );


    @Query("select w from Work w where (w.category.id = ?1 or ?1 = null) and (w.project.id = ?2 or ?2 = null)" +
    "and (w.project.client.id = ?3 or ?3 = null) and (w.project.lead.id = ?4 or ?4 = null) and " +
            "(w.date >= ?5 or ?5 = null) and (w.date <= ?6 or ?6 = null)")
    List<Work> search(Long categoryId, Long projectId, Long clientId, Long leadId, Date startDate, Date endDate);
}
