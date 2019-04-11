package vega.it.praksa.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vega.it.praksa.model.*;
import vega.it.praksa.model.dtos.*;
import vega.it.praksa.repositories.*;

@Mapper(uses = {CountryRepository.class, ClientRepository.class, EmployeeRepository.class,
        CategoryRepository.class, ProjectRepository.class, EmployeeRepository.class})
public interface DtoMapper {

    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);

    ClientOutputDto clientToClientDto(Client client);
    @Mapping(source = "country", target = "country", qualifiedByName = "longToCountry")
    Client clientInputDtoToClient(ClientInputDto clientInputDto);

    Country countryDtoToCountry (CountryDto countryDto);
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

    Team teamDtoToTeam (TeamInputDto teamInputDto);
    TeamOutputDto teamToTeamOutputDto(Team team);


    @Named("longToCountry")
    default Country longToCountry(Long id, @Context CountryRepository countryRepository) {
        return countryRepository.findById(id).get();
    }

    @Named("longToClient")
    default Client longToClient(Long id, @Context ClientRepository clientRepository) {
        return clientRepository.findById(id).get();
    }

    @Named("longToCountry")
    default Employee longToTeammember(Long id, @Context EmployeeRepository employeeRepository) {
        return employeeRepository.findById(id).get();
    }

    @Named("longToCategory")
    default Category longToCategory(Long id, @Context CategoryRepository categoryRepository) {
        return categoryRepository.findById(id).get();
    }

    @Named("longToProject")
    default Project longToProject(Long id, @Context ProjectRepository projectRepository) {
        return projectRepository.findById(id).get();
    }

    @Named("longToEmployee")
    default Employee longToEmployee(Long id, @Context EmployeeRepository employeeRepository) {
        return employeeRepository.findById(id).get();
    }

    @Named("projectToString")
    default String projectToString(Project project) {
        return project.getName();
    }
}
