package vega.it.praksa.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.model.*;
import vega.it.praksa.model.dtos.*;
import vega.it.praksa.repositories.*;

@Mapper(
        uses = {
            CountryRepository.class,
            ClientRepository.class,
            EmployeeRepository.class,
            CategoryRepository.class,
            ProjectRepository.class,
            EmployeeRepository.class,
            ProjectMemberRepository.class
        })
public interface DtoMapper {

    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToCategoryDto(Category category);

    ClientOutputDto clientToClientDto(Client client);

    @Mapping(source = "country", target = "country", qualifiedByName = "longToCountry")
    Client clientInputDtoToClient(ClientInputDto clientInputDto);

    Country countryDtoToCountry(CountryDto countryDto);

    CountryDto countryToCountryDto(Country country);

    ProjectOutputDto projectToProjectDto(Project project);

    Project projectInputDtoToProject(ProjectInputDto projectInputDto);

    Employee employeeDtoToEmployee(EmployeeInputDto employeeInputDto);

    EmployeeOutputDto employeeToEmployeeOutputDto(Employee employee);

    ProjectMember projectMemberDtoToProjectMember(ProjectMemberInputDto projectMemberInputDto);

    @Mapping(source = "project", target = "project", qualifiedByName = "projectToString")
    ProjectMemberOutputDto projectMemberToProjectMemberOutputDto(ProjectMember projectMember);

    WorkDto workToWorkDto(Work work);

    Work workInputDtoToWork(WorkInputDto workInputDto);

    Team teamDtoToTeam(TeamInputDto teamInputDto);

    TeamOutputDto teamToTeamOutputDto(Team team);

    Vacation vacationDtoToVacation(VacationInputDto vacationInputDto);

    VacationOutputDto vacationToVacationOutputDto(Vacation vacation);

    @Named("longToCountry")
    default Country longToCountry(Long id, @Context CountryRepository countryRepository) {
        return countryRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Country with id '" + id + "' is not found"));
    }

    @Named("longToClient")
    default Client longToClient(Long id, @Context ClientRepository clientRepository) {
        return clientRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Client with id '" + id + "' is not found"));
    }

    @Named("longToCountry")
    default Employee longToTeammember(Long id, @Context EmployeeRepository employeeRepository) {
        return employeeRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Employee with id '" + id + "' is not found"));
    }

    @Named("longToCategory")
    default Category longToCategory(Long id, @Context CategoryRepository categoryRepository) {
        return categoryRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Category with id '" + id + "' is not found"));
    }

    @Named("longToProject")
    default Project longToProject(Long id, @Context ProjectRepository projectRepository) {
        return projectRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Project with id '" + id + "' is not found"));
    }

    @Named("longToEmployee")
    default Employee longToEmployee(Long id, @Context EmployeeRepository employeeRepository) {
        return employeeRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Employee with id '" + id + "' is not found"));
    }

    @Named("longToProjectMember")
    default ProjectMember longToProjectMember(
            Long id, @Context ProjectMemberRepository projectMemberRepository) {
        return projectMemberRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Employee with id '" + id + "' is not found"));
    }

    @Named("projectToString")
    default String projectToString(Project project) {
        return project.getName();
    }
}
