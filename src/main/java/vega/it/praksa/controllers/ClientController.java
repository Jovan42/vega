package vega.it.praksa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.ClientDto;
import vega.it.praksa.model.dtos.ClientListDto;
import vega.it.praksa.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController extends GenericCrudControllerImpl<ClientDto, ClientListDto, Long, ClientService> {

    public ClientController(ClientService clientService) {
        super(clientService);
    }
}
