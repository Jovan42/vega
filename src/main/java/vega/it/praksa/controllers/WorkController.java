package vega.it.praksa.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.WorkDto;
import vega.it.praksa.model.dtos.WorkInputDto;
import vega.it.praksa.model.dtos.WorkListDto;
import vega.it.praksa.services.WorkService;

import javax.websocket.server.PathParam;
import java.util.Date;

@RestController
@RequestMapping("/api/works")
@Slf4j
public class WorkController extends GenericCrudControllerImpl<WorkInputDto, WorkDto, WorkListDto, Long, WorkService> {

    @Autowired
    public WorkController(WorkService workService) {
        super(workService);
    }

    @GetMapping("/for-day")
    public ResponseEntity<WorkListDto> getForDay(@PathParam("year") String year, @PathParam("month") String month,
                                                 @PathParam("day") String day ) {
         return new ResponseEntity<>(service.getForDay(year, month, day), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<WorkListDto> search ( @PathParam("category") String category,
                                                @PathParam("project") String project,
                                                @PathParam("client") String client,
                                                @PathParam("teamMember") String lead,
                                                @PathParam("startDate")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                @PathParam("endDate")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){

        return new ResponseEntity<>(service.search(category, project, client, lead, startDate, endDate), HttpStatus.OK);
    }
}
