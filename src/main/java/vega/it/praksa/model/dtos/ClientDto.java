package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClientDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    private CountryDto country;
}
