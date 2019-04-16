package vega.it.praksa.services.jpa;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.exceptions.UnauthorizedException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.Vacation;
import vega.it.praksa.model.dtos.VacationInputDto;
import vega.it.praksa.model.dtos.VacationListDto;
import vega.it.praksa.model.dtos.VacationOutputDto;
import vega.it.praksa.repositories.EmployeeRepository;
import vega.it.praksa.repositories.TeamRepository;
import vega.it.praksa.repositories.VacationRepository;
import vega.it.praksa.services.VacationService;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class VacationServiceImpl implements VacationService {
    private VacationRepository vacationRepository;
    private EmployeeRepository employeeRepository;
    private MailingServiceImpl mailingService;
    private TeamRepository teamRepository;
    private DtoMapper mapper;

    public VacationServiceImpl(
            VacationRepository vacationRepository,
            EmployeeRepository employeeRepository,
            MailingServiceImpl mailingService,
            TeamRepository teamRepository,
            DtoMapper mapper) {
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
        this.mailingService = mailingService;
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    @Override
    public VacationListDto get() {
        return new VacationListDto(
                vacationRepository.findAll().stream()
                        .map(mapper::vacationToVacationOutputDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public VacationOutputDto get(Long id) {

        Vacation vacation =
                vacationRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Country with id '" + id + "' is not found"));
        if (!checkAuthority(vacation))
            throw new UnauthorizedException(
                    "You are not authorized to see and/or approve this vacation");
        return mapper.vacationToVacationOutputDto(vacation);
    }

    @Override
    public VacationOutputDto add(VacationInputDto vacationInputDto) {
        vacationInputDto.setId(null);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeRepository.getByUsername(auth.getName()).get();
        vacationInputDto.setEmployee(employee.getId());

        Vacation vacation = vacationRepository.save(mapper.vacationDtoToVacation(vacationInputDto));
        mailingService.sendMailForVacation(vacation);

        return mapper.vacationToVacationOutputDto(vacation);
    }

    @Override
    public VacationOutputDto update(VacationInputDto vacationInputDto) {
        return mapper.vacationToVacationOutputDto(
                vacationRepository.save(mapper.vacationDtoToVacation(vacationInputDto)));
    }

    @Override
    public void delete(Long id) {
        vacationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void approve(Long vacationId) {
        Vacation vacation =
                vacationRepository
                        .findById(vacationId)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Country with id '"
                                                        + vacationId
                                                        + "' is not found"));
        if (!checkAuthority(vacation))
            throw new UnauthorizedException(
                    "You are not authorized to see and/or approve this vacation");

        vacation.setApproved(true);
        vacationRepository.save(vacation);
    }

    @Override
    public void deny(Long vacationId) {
        Vacation vacation =
                vacationRepository
                        .findById(vacationId)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Country with id '"
                                                        + vacationId
                                                        + "' is not found"));
        if (!checkAuthority(vacation))
            throw new UnauthorizedException(
                    "You are not authorized to see and/or approve this vacation");

        vacation.setApproved(false);
        vacationRepository.save(vacation);
    }

    @Override
    public VacationListDto findForEmployeeBetweenDates(
            Long employeeId, Date starDate, Date endDate) {
        return new VacationListDto(
                vacationRepository
                        .findAllByEmployee_IdAndEndDateGreaterThanEqualAndStartDateIsLessThanEqual(
                                employeeId, starDate, endDate)
                        .stream()
                        .map(mapper::vacationToVacationOutputDto)
                        .collect(Collectors.toList()));
    }

    private boolean checkAuthority(Vacation vacation) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Employee employee =
                employeeRepository
                        .getByUsername(auth.getName())
                        .orElseThrow(
                                () ->
                                        new UnauthorizedException(
                                                "You are not authorized to see and/or approve this vacation"));

        return teamRepository
                        .findAllByTeamLeader_IdAndEmployeesContains(
                                employee.getId(), vacation.getEmployee())
                        .size()
                > 0;
    }
}
