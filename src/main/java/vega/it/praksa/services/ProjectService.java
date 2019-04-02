package vega.it.praksa.services;

import vega.it.praksa.model.dtos.ProjectDto;
import vega.it.praksa.model.dtos.ProjectListDto;

public interface ProjectService extends GenericService<ProjectDto, ProjectListDto, Long>{
    ProjectListDto getByName(String name);
    ProjectListDto getByFirstLetter(String letter);
    ProjectListDto getByClient(Long clientId);
}
