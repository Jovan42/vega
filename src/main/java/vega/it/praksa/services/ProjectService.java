package vega.it.praksa.services;

import vega.it.praksa.model.dtos.ProjectInputDto;
import vega.it.praksa.model.dtos.ProjectListDto;
import vega.it.praksa.model.dtos.ProjectOutputDto;

public interface ProjectService
        extends GenericService<ProjectInputDto, ProjectOutputDto, ProjectListDto, Long> {
    ProjectListDto getByName(String name);

    ProjectListDto getByFirstLetter(String letter);

    ProjectListDto getByClient(Long clientId);
}
