package vega.it.praksa.services;

import org.springframework.stereotype.Service;
import vega.it.praksa.model.dtos.ClientDto;
import vega.it.praksa.model.dtos.ClientListDto;

@Service
public interface ClientService extends GenericService<ClientDto, ClientListDto, Long> {
}
