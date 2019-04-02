package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.exceptions.BadRequestException;
import vega.it.praksa.model.dtos.WorkDto;
import vega.it.praksa.model.dtos.WorkListDto;
import vega.it.praksa.services.WorkService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/works")
public class WorkController extends GenericCrudControllerImpl<WorkDto, WorkListDto, Long, WorkService> {

    @Autowired
    public WorkController(WorkService workService) {
        super(workService);
    }

    @GetMapping("/forDay")
    public ResponseEntity<WorkListDto> getForDay(@PathParam("year") String year, @PathParam("month") String month,
                                                 @PathParam("day") String day ) {
         return new ResponseEntity<>(service.getForDay(year, month, day), HttpStatus.OK);

    }
}
