package vega.it.praksa.services;

import vega.it.praksa.model.dtos.ProjectMemberInputDto;
import vega.it.praksa.model.dtos.ProjectMemberListDto;
import vega.it.praksa.model.dtos.ProjectMemberOutputDto;

public interface ProjectMemberService
        extends GenericService<
                ProjectMemberInputDto, ProjectMemberOutputDto, ProjectMemberListDto, Long> {

    ProjectMemberListDto getForProject(Long projectId);
}
