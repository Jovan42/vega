package vega.it.praksa.model.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectMemberInputDto {
    private Long id;

    @NotNull private Long employee;

    @NotNull private Long project;

    @NotNull private Double dailyAllocation;
}
