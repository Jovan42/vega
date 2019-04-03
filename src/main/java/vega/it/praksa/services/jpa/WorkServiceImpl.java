package vega.it.praksa.services.jpa;

import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.BadRequestException;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Work;
import vega.it.praksa.model.dtos.WorkDto;
import vega.it.praksa.model.dtos.WorkListDto;
import vega.it.praksa.repositories.WorkRepository;
import vega.it.praksa.services.WorkService;

import java.util.ArrayList;
import java.util.Date;
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
    public WorkListDto getForDay(String year, String month, String day ){
        List<String> errorMessages = new ArrayList<>();
        if(!year.matches("\\d*"))   errorMessages.add("Wrong values for year");
        if(!month.matches("\\d*")) errorMessages.add("Wrong values for month");
        if(!day.matches("\\d*")) errorMessages.add("Wrong values for day");
        if(errorMessages.size() != 0)
              throw  new BadRequestException(errorMessages);


        List<WorkDto> works =  workRepository.getForDay(Integer.parseInt(year), Integer.parseInt(month),
                Integer.parseInt(day))
                    .stream()
                    .map(mapper::workToWorkDto)
                    .collect(Collectors.toList());
        return new WorkListDto(works);
    }

    @Override
    public WorkListDto search(String category, String project, String client, String lead, Date startDate, Date endDate) {
        Long dCategory, dProject, dClient, dLead;
        if(category == null) dCategory = null;
        else dCategory = Long.parseLong(category);

        if(project == null) dProject = null;
        else dProject = Long.parseLong(project);

        if(client == null) dClient = null;
        else dClient = Long.parseLong(client);

        if(lead == null) dLead = null;
        else dLead = Long.parseLong(lead);

        List<WorkDto> works =  workRepository.search(dCategory, dProject, dClient, dLead, startDate, endDate)
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
