package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.WorkDto;
import vega.it.praksa.model.dtos.WorkListDto;
import vega.it.praksa.services.WorkService;

@RestController
@RequestMapping("/api/works")
public class WorkController extends GenericCrudControllerImpl<WorkDto, WorkListDto, Long, WorkService> {

    @Autowired
    public WorkController(WorkService workService) {
        super(workService);
    }
}
