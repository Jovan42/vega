package vega.it.praksa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.CountryDto;
import vega.it.praksa.model.dtos.CountryListDto;
import vega.it.praksa.services.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController extends GenericCrudControllerImpl<CountryDto, CountryListDto, Long, CountryService> {

    public CountryController(CountryService countryService) {
        super(countryService);
    }
}
