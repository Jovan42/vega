package vega.it.praksa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import vega.it.praksa.services.GenericService;

import javax.validation.Valid;

public class GenericCrudControllerImpl<T_INPUT_DTO, T_OUTPUT_DTO, T_COLLECTION, IDENTIFIER, SERVICE
            extends GenericService<T_INPUT_DTO,T_OUTPUT_DTO, T_COLLECTION, IDENTIFIER> >
        implements GenericCrudController<T_INPUT_DTO, T_OUTPUT_DTO, T_COLLECTION, IDENTIFIER> {

    SERVICE service;

    public GenericCrudControllerImpl(SERVICE service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<T_COLLECTION> get() {
        return new ResponseEntity<>(service.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T_OUTPUT_DTO> get(IDENTIFIER id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T_OUTPUT_DTO> add(@Valid T_INPUT_DTO t_dto, Errors errors) {
        return new ResponseEntity<>(service.add(t_dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T_OUTPUT_DTO> update(@Valid T_INPUT_DTO t_dto, Errors errors) {
        return new ResponseEntity<>(service.update(t_dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(IDENTIFIER id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
