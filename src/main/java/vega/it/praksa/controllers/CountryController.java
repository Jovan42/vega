package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.CountryDto;
import vega.it.praksa.model.dtos.CountryListDto;
import vega.it.praksa.services.CountryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/country")
public class CountryController implements GenericCrudController<CountryDto, CountryListDto, Long>{
    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public ResponseEntity<CountryListDto> get() {
        return new ResponseEntity<>(countryService.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CountryDto> get(Long id) {
        return new ResponseEntity<>(countryService.get(id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CountryDto> add(@Valid CountryDto countryDto, Errors errors) {
        return new ResponseEntity<>(countryService.add(countryDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CountryDto> update(@Valid CountryDto countryDto, Errors errors) {
        return new ResponseEntity<>(countryService.update(countryDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
