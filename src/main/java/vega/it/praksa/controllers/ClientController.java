package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.ClientDto;
import vega.it.praksa.model.dtos.ClientListDto;
import vega.it.praksa.services.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController implements GenericCrudController<ClientDto, ClientListDto, Long>{
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientListDto> get() {
        return new ResponseEntity<>(clientService.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ClientDto> get(Long id) {
        return new ResponseEntity<>(clientService.get(id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ClientDto> add(@Valid ClientDto clientDto, Errors errors) {
        return new ResponseEntity<>(clientService.add(clientDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ClientDto> update(@Valid ClientDto clientDto, Errors errors) {
        return new ResponseEntity<>(clientService.update(clientDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
