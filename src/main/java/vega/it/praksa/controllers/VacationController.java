package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vega.it.praksa.model.dtos.VacationInputDto;
import vega.it.praksa.model.dtos.VacationListDto;
import vega.it.praksa.model.dtos.VacationOutputDto;
import vega.it.praksa.services.VacationService;

import javax.websocket.server.PathParam;
import java.util.Date;

@RestController
@RequestMapping("/api/vacations")
public class VacationController
        extends GenericCrudControllerImpl<
                VacationInputDto, VacationOutputDto, VacationListDto, Long, VacationService> {

    @Autowired
    public VacationController(VacationService service) {
        super(service);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        service.approve(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/deny")
    public ResponseEntity<Void> deny(@PathVariable Long id) {
        service.deny(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/for-timeline")
    public ResponseEntity<VacationListDto> getForTimeline(
            @PathParam("employee") Long employee,
            @PathParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return new ResponseEntity<>(
                service.findForEmployeeBetweenDates(employee, startDate, endDate), HttpStatus.OK);
    }
}
