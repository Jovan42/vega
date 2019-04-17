package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Team;
import vega.it.praksa.model.dtos.EmployeeListDto;
import vega.it.praksa.model.dtos.TeamInputDto;
import vega.it.praksa.model.dtos.TeamListDto;
import vega.it.praksa.model.dtos.TeamOutputDto;
import vega.it.praksa.repositories.EmployeeRepository;
import vega.it.praksa.repositories.TeamRepository;
import vega.it.praksa.services.TeamService;

import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private EmployeeRepository employeeRepository;
    private DtoMapper mapper;

    @Autowired
    public TeamServiceImpl(
            TeamRepository teamRepository,
            EmployeeRepository employeeRepository,
            DtoMapper mapper) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public TeamListDto get() {
        return new TeamListDto(
                teamRepository.findAll().stream()
                        .map(mapper::teamToTeamOutputDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public TeamOutputDto get(Long id) {
        return teamRepository
                .findById(id)
                .map(mapper::teamToTeamOutputDto)
                .orElseThrow(() -> new NotFoundException("Team with id '" + id + "' is not found"));
    }

    @Override
    public TeamOutputDto add(TeamInputDto teamInputDto) {
        teamInputDto.setId(null);
        return mapper.teamToTeamOutputDto(teamRepository.save(mapper.teamDtoToTeam(teamInputDto)));
    }

    @Override
    public TeamOutputDto update(TeamInputDto teamInputDto) {
        return mapper.teamToTeamOutputDto(teamRepository.save(mapper.teamDtoToTeam(teamInputDto)));
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public EmployeeListDto getEmployees(Long id) {
        return new EmployeeListDto(
                teamRepository
                        .findById(id)
                        .map(mapper::teamToTeamOutputDto)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Team with id '" + id + "' is not found"))
                        .getEmployees());
    }

    @Override
    public void addEmployee(Long id, Long employeeId) {
        Team team =
                teamRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Team with id '" + id + "' is not found"));
        if (!team.getEmployees()
                .contains(
                        employeeRepository
                                .findById(employeeId)
                                .orElseThrow(
                                        () ->
                                                new NotFoundException(
                                                        "Team with id '" + id + "' is not found"))))
            team.getEmployees()
                    .add(
                            employeeRepository
                                    .findById(employeeId)
                                    .orElseThrow(
                                            () ->
                                                    new NotFoundException(
                                                            "Team with id '"
                                                                    + id
                                                                    + "' is not found")));
        teamRepository.save(team);
    }

    @Override
    public void removeEmployee(Long id, Long employeeId) {
        Team team =
                teamRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Team with id '" + id + "' is not found"));

        team.getEmployees()
                .remove(
                        employeeRepository
                                .findById(employeeId)
                                .orElseThrow(
                                        () ->
                                                new NotFoundException(
                                                        "Team with id '" + id + "' is not found")));
        teamRepository.save(team);
    }
}
