package vega.it.praksa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface GenericCrudController<T_DTO, T_COLLECTION, IDENTIFIER> {
    @GetMapping(path="", produces = "application/json")
    ResponseEntity<T_COLLECTION> get();

    @GetMapping(path = "/{id}", produces = "application/json" )
    ResponseEntity<T_DTO> get(@PathVariable  IDENTIFIER id);

    @PostMapping("")
    ResponseEntity<T_DTO> add(@Valid @RequestBody T_DTO dto, Errors errors);

    @PutMapping("")
    ResponseEntity<T_DTO> update(@Valid @RequestBody T_DTO dto, Errors errors);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable IDENTIFIER id);
}
