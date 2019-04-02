package vega.it.praksa.mappers;

import org.mapstruct.Mapper;
import vega.it.praksa.model.*;
import vega.it.praksa.model.dtos.*;

@Mapper
public interface DtoMapper {
    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);

    Client clientDtoToClient(ClientDto clientDto);
    ClientDto clientToClientDto(Client client);

    Country countryDtoToCountry (CountryDto countryDto);
    CountryDto countryToCountryDto(Country country);

    Project projectDtoToProject (ProjectDto projectDto);
    ProjectDto projectToProjectDto(Project project);

    Report reportDtoToReport (ReportDto reportDto);
    ReportDto reportToReportDto(Report report);

    TeamMember teamMemberDtoToTeamMember(TeamMemberDto teamMemberDto);
    TeamMemberDto teamMemberToTeamMemberDto(TeamMember teamMember);
}
