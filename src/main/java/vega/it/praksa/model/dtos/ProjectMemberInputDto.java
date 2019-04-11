package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberInputDto {
    private Long id;

    @NotNull
    private Long employee;

    @NotNull
    private Long project;

    @NotNull
    private Double dailyAllocation;
}
