package vega.it.praksa.model.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CountryDto {
    private Long id;
    @NotBlank private String name;
}
