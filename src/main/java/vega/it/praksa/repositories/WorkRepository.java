package vega.it.praksa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vega.it.praksa.model.Work;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    //@Query("select * from table where month(birthdate)=03 and year(birthdate)=1990")
    //List<WorkDto> findAllByDate_DayAndDate_MonthAndDate_Year(int day, int month, int year);

    @Query("select e from Work e where year(e.date) = ?1 and  month(e.date) = ?2 and day(e.date) = ?3")
    List<Work> getAllOfCurrentMonth(int year, int month, int day );

}
