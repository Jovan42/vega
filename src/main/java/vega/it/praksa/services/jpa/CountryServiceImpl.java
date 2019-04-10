package vega.it.praksa.services.jpa;

import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Country;
import vega.it.praksa.model.dtos.CountryDto;
import vega.it.praksa.model.dtos.CountryListDto;
import vega.it.praksa.repositories.CountryRepository;
import vega.it.praksa.services.CountryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;
    private DtoMapper mapper;

    public CountryServiceImpl(CountryRepository countryRepository, DtoMapper mapper) {
        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public CountryListDto get() {
        List<CountryDto> countries =  countryRepository.findAll()
                .stream()
                .map(mapper::countryToCountryDto)
                .collect(Collectors.toList());
        return new CountryListDto(countries);
    }

    @Override
    public CountryDto get(Long id) {
        return countryRepository.findById(id)
                .map(mapper::countryToCountryDto)
                .orElseThrow(()-> new NotFoundException("Country with id '" + id +"' is not found"));
    }

    @Override
    public CountryDto add(CountryDto countryDto) {
        countryDto.setId(null);
        return mapper.countryToCountryDto(
                countryRepository.save(mapper.countryDtoToCountry(countryDto))
        );
    }

    @Override
    public CountryDto update(CountryDto countryDto) {
        return mapper.countryToCountryDto(
            countryRepository.save(mapper.countryDtoToCountry(countryDto))
        );
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
