package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.ClientInputDto;
import vega.it.praksa.model.dtos.ClientListDto;
import vega.it.praksa.model.dtos.ClientOutputDto;
import vega.it.praksa.services.ClientService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/clients")
public class ClientController
        extends GenericCrudControllerImpl<
                ClientInputDto, ClientOutputDto, ClientListDto, Long, ClientService> {
    @Autowired
    public ClientController(ClientService clientService) {
        super(clientService);
    }

    @GetMapping("/by-name")
    public ResponseEntity<ClientListDto> getByName(@PathParam("name") String name) {
        return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/first-letter")
    public ResponseEntity<ClientListDto> getByFirstLetter(@PathParam("letter") String letter) {
        return new ResponseEntity<>(service.getByFirstLetter(letter), HttpStatus.OK);
    }
}
