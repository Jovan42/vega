package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.BadRequestException;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Client;
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
    public ClientListDto getByName(String name) {
        if(name == null || name.trim().equals(""))
            throw new BadRequestException("Attribute name can not be empty");

        List<ClientDto> clients =  clientRepository.findAllByNameContaining(name)
                .stream()
                .map(mapper::clientToClientDto)
                .collect(Collectors.toList());
        return new ClientListDto(clients);
    }

    @Override
    public ClientListDto getByFirstLetter(String letter) {
        if(letter == null || letter.trim().equals(""))
            throw new BadRequestException("Attribute letter can not be empty");

        List<ClientDto> clients =  clientRepository.findAllByNameStartsWithOrNameStartsWith(letter.toUpperCase(),
                letter.toLowerCase())
                .stream()
                .map(mapper::clientToClientDto)
                .collect(Collectors.toList());
        return new ClientListDto(clients);
    }

    @Override
    public ClientDto get(Long id) {
        return clientRepository.findById(id)
                .map(mapper::clientToClientDto)
                .orElseThrow(()-> new NotFoundException("Client with id '" + id +"' is not found"));
    }

    @Override
    public ClientDto add(ClientDto clientDto) {
        clientDto.setId(null);
        return mapper.clientToClientDto(
                clientRepository.save(mapper.clientDtoToClient(clientDto))
        );
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        return mapper.clientToClientDto(
                clientRepository.save(mapper.clientDtoToClient(clientDto))
        );
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
