package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Client;
import vega.it.praksa.model.dtos.CategoryDto;
import vega.it.praksa.model.dtos.CategoryListDto;
import vega.it.praksa.model.dtos.ClientDto;
import vega.it.praksa.model.dtos.ClientListDto;
import vega.it.praksa.repositories.ClientRepository;
import vega.it.praksa.services.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<ClientDto> clients =  clientRepository.findAll()
                .stream()
                .map(mapper::clientToClientDto)
                .collect(Collectors.toList());
        return new ClientListDto(clients);
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