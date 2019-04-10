package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.ProjectOutputDto;
import vega.it.praksa.model.dtos.ProjectInputDto;
import vega.it.praksa.model.dtos.ProjectListDto;
import vega.it.praksa.services.ProjectService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/projects")
public class ProjectController extends GenericCrudControllerImpl<ProjectInputDto, ProjectOutputDto, ProjectListDto, Long, ProjectService> {
    @Autowired
    public ProjectController(ProjectService service) {
        super(service);
    }

    @GetMapping("/by-name")
    public ResponseEntity<ProjectListDto> getByName(@PathParam("name") String name) {
        return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/first-letter")
    public ResponseEntity<ProjectListDto> getByFirstLetter(@PathParam("letter") String letter) {
        return new ResponseEntity<>(service.getByFirstLetter(letter), HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<ProjectListDto> getByFirstLetter(@PathParam("clientId") Long clientId) {
        return new ResponseEntity<>(service.getByClient(clientId), HttpStatus.OK);
    }
}
