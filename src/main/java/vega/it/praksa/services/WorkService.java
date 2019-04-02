package vega.it.praksa.services;

import vega.it.praksa.model.dtos.WorkDto;
import vega.it.praksa.model.dtos.WorkListDto;

public interface WorkService extends GenericService<WorkDto, WorkListDto, Long> {
    WorkListDto getForDay(String year, String month, String day );
}
