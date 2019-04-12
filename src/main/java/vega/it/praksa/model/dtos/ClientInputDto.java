package vega.it.praksa.model.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class ClientInputDto {
    private Long id;
    @NotBlank private String name;
    @NotBlank private String address;
    @NotBlank private String city;
    @NotBlank private String zipCode;
    @NotNull private Long country;
}
