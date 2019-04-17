package vega.it.praksa.services;

import vega.it.praksa.model.dtos.VacationInputDto;
import vega.it.praksa.model.dtos.VacationListDto;
import vega.it.praksa.model.dtos.VacationOutputDto;

import java.util.Date;

public interface VacationService
        extends GenericService<VacationInputDto, VacationOutputDto, VacationListDto, Long> {

    void approve(Long vacationId);

    void deny(Long vacationId);

    VacationListDto findForEmployeeBetweenDates(Long employeeId, Date starDate, Date endDate);
}
