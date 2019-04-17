package vega.it.praksa.services.jpa;

import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.dtos.ProjectMemberInputDto;
import vega.it.praksa.model.dtos.ProjectMemberListDto;
import vega.it.praksa.model.dtos.ProjectMemberOutputDto;
import vega.it.praksa.repositories.ProjectMemberRepository;
import vega.it.praksa.services.ProjectMemberService;

import java.util.stream.Collectors;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private ProjectMemberRepository projectMemberRepository;
    private DtoMapper mapper;

    public ProjectMemberServiceImpl(
            ProjectMemberRepository projectMemberRepository, DtoMapper mapper) {
        this.projectMemberRepository = projectMemberRepository;
        this.mapper = mapper;
    }

    @Override
    public ProjectMemberListDto get() {
        return new ProjectMemberListDto(
                projectMemberRepository.findAll().stream()
                        .map(mapper::projectMemberToProjectMemberOutputDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public ProjectMemberOutputDto get(Long id) {
        return projectMemberRepository
                .findById(id)
                .map(mapper::projectMemberToProjectMemberOutputDto)
                .orElseThrow(
                        () -> new NotFoundException("Country with id '" + id + "' is not found"));
    }

    @Override
    public ProjectMemberOutputDto add(ProjectMemberInputDto projectMemberInputDto) {
        projectMemberInputDto.setId(null);
        return mapper.projectMemberToProjectMemberOutputDto(
                projectMemberRepository.save(
                        mapper.projectMemberDtoToProjectMember(projectMemberInputDto)));
    }

    @Override
    public ProjectMemberOutputDto update(ProjectMemberInputDto projectMemberInputDto) {
        return mapper.projectMemberToProjectMemberOutputDto(
                projectMemberRepository.save(
                        mapper.projectMemberDtoToProjectMember(projectMemberInputDto)));
    }

    @Override
    public void delete(Long id) {
        projectMemberRepository.deleteById(id);
    }

    @Override
    public ProjectMemberListDto getForProject(Long projectId) {
        return new ProjectMemberListDto(
                projectMemberRepository.findAllByProject_Id(projectId).stream()
                        .map(mapper::projectMemberToProjectMemberOutputDto)
                        .collect(Collectors.toList()));
    }
}
