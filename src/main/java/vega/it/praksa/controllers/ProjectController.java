package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.ProjectDto;
import vega.it.praksa.model.dtos.ProjectListDto;
import vega.it.praksa.services.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController extends GenericCrudControllerImpl<ProjectDto, ProjectListDto, Long, ProjectService> {
    @Autowired
    public ProjectController(ProjectService service) {
        super(service);
    }
}
