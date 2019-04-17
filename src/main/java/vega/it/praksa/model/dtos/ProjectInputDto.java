package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInputDto {
    private Long id;
    @NotBlank private String name;
    @NotBlank private String description;
    @NotNull private Long client;
    @NotNull private Long lead;
}
