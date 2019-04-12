package vega.it.praksa.services;

import org.springframework.stereotype.Service;
import vega.it.praksa.model.dtos.ClientInputDto;
import vega.it.praksa.model.dtos.ClientListDto;
import vega.it.praksa.model.dtos.ClientOutputDto;

@Service
public interface ClientService
        extends GenericService<ClientInputDto, ClientOutputDto, ClientListDto, Long> {
    ClientListDto getByName(String name);

    ClientListDto getByFirstLetter(String letter);
}
