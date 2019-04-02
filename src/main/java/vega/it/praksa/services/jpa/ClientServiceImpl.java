package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Client;
import vega.it.praksa.model.dtos.ClientDto;
import vega.it.praksa.model.dtos.ClientListDto;
import vega.it.praksa.repositories.ClientRepository;
import vega.it.praksa.services.ClientService;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private DtoMapper mapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, DtoMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientListDto get() {
        return new ClientListDto(clientRepository.findAll());
    }

    @Override
    public ClientDto get(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent())
            return mapper.clientToClientDto(client.get());
        else
            throw new NotFoundException("Client with id '" + id +"' is not found");
    }

    @Override
    public ClientDto add(ClientDto clientDto) {
        clientDto.setId(null);
        Client client = clientRepository.save(mapper.clientDtoToClient(clientDto));
        return mapper.clientToClientDto(client);
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        Client client = clientRepository.save(mapper.clientDtoToClient(clientDto));
        return mapper.clientToClientDto(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
