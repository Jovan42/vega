package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import vega.it.praksa.services.GenericService;

import javax.validation.Valid;

public class GenericCrudControllerImpl<T_DTO , T_COLLECTION, IDENTIFIER, SERVICE
        extends GenericService<T_DTO, T_COLLECTION, IDENTIFIER> >
        implements GenericCrudController<T_DTO, T_COLLECTION, IDENTIFIER> {
    SERVICE service;

    @Autowired
    public GenericCrudControllerImpl(SERVICE service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<T_COLLECTION> get() {
        return new ResponseEntity<>(service.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T_DTO> get(IDENTIFIER id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T_DTO> add(@Valid T_DTO t_dto, Errors errors) {
        return new ResponseEntity<>(service.add(t_dto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<T_DTO> update(@Valid T_DTO t_dto, Errors errors) {
        return new ResponseEntity<>(service.update(t_dto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> delete(IDENTIFIER id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
