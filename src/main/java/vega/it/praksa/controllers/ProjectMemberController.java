package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.*;
import vega.it.praksa.services.ProjectMemberService;


@RestController
@RequestMapping("/api/project-members")
public class ProjectMemberController extends GenericCrudControllerImpl<ProjectMemberInputDto, ProjectMemberOutputDto,
        ProjectMemberListDto, Long, ProjectMemberService>{
    @Autowired
    public ProjectMemberController(ProjectMemberService projectMemberService) {
        super(projectMemberService);
    }

}
