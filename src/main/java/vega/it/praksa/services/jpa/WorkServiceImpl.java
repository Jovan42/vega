package vega.it.praksa.services.jpa;

import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Work;
import vega.it.praksa.model.dtos.WorkDto;
import vega.it.praksa.model.dtos.WorkListDto;
import vega.it.praksa.repositories.WorkRepository;
import vega.it.praksa.services.WorkService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl implements WorkService {
    private WorkRepository workRepository;
    private DtoMapper mapper;

    public WorkServiceImpl(WorkRepository workRepository, DtoMapper mapper) {
        this.workRepository = workRepository;
        this.mapper = mapper;
    }

    @Override
    public WorkListDto get() {
        List<WorkDto> works =  workRepository.findAll()
                .stream()
                .map(mapper::workToWorkDto)
                .collect(Collectors.toList());
        return new WorkListDto(works);
    }

    @Override
    public WorkDto get(Long id) {
        Optional<Work> work = workRepository.findById(id);
        if(work.isPresent())
            return mapper.workToWorkDto(work.get());
        else
            throw new NotFoundException("Work with id '" + id +"' is not found");
    }

    @Override
    public WorkDto add(WorkDto workDto) {
        workDto.setId(null);
        Work work = workRepository.save(mapper.workDtoToWork(workDto));
        return mapper.workToWorkDto(work);
    }

    @Override
    public WorkDto update(WorkDto workDto) {
        Work work = workRepository.save(mapper.workDtoToWork(workDto));
        return mapper.workToWorkDto(work);
    }

    @Override
    public void delete(Long id) {
        workRepository.deleteById(id);
    }
}
