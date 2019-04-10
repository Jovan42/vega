package vega.it.praksa.services;

import vega.it.praksa.model.dtos.WorkDto;
import vega.it.praksa.model.dtos.WorkInputDto;
import vega.it.praksa.model.dtos.WorkListDto;

import java.util.Date;

public interface WorkService extends GenericService<WorkInputDto, WorkDto, WorkListDto, Long> {
    WorkListDto getForDay(String year, String month, String day );
    WorkListDto search(String category, String project, String client, String lead, Date startDate, Date endDate);
}
