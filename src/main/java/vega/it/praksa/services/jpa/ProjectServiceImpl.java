package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Client;
import vega.it.praksa.model.Project;
import vega.it.praksa.model.dtos.ProjectDto;
import vega.it.praksa.model.dtos.ProjectListDto;
import vega.it.praksa.repositories.ClientRepository;
import vega.it.praksa.repositories.ProjectRepository;
import vega.it.praksa.services.ProjectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private ClientRepository clientRepository;
    private DtoMapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ClientRepository clientRepository, DtoMapper mapper) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public ProjectListDto get() {
        return new ProjectListDto(projectRepository.findAll()
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ProjectDto get(Long id) {
        return projectRepository.findById(id)
                .map(mapper::projectToProjectDto)
                .orElseThrow(()-> new NotFoundException("Country with id '" + id +"' is not found"));
    }

    @Override
    public ProjectDto add(ProjectDto projectDto) {
        projectDto.setId(null);
        Project project = projectRepository.save(mapper.projectDtoToProject(projectDto));
        return mapper.projectToProjectDto(project);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        Project project = projectRepository.save(mapper.projectDtoToProject(projectDto));
        return mapper.projectToProjectDto(project);
    }

    @Override
    public void delete(Long id) {
            projectRepository.deleteById(id);
    }

    @Override
    public ProjectListDto getByName(String name) {
        List<ProjectDto> projects=  projectRepository.findAllByNameContaining(name)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
        return new ProjectListDto(projects);
    }

    @Override
    public ProjectListDto getByFirstLetter(String letter) {
        List<ProjectDto> projects=  projectRepository.findAllByNameStartsWithOrNameStartsWith(letter.toUpperCase(),
                letter.toLowerCase())
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
        return new ProjectListDto(projects);
    }


    @Override
    public ProjectListDto getByClient(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(!client.isPresent()) {
            throw new NotFoundException("Client with id '" + clientId +"' is not found");
        }
        List<ProjectDto> projects=  projectRepository.findAllByClient(client.get())
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
        return new ProjectListDto(projects);
    }
}
