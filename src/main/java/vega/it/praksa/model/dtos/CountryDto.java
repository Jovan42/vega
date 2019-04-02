package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CountryDto {
    private Long id;
    private String name;
}
