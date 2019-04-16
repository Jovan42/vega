package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationOutputDto {
    private Long id;

    private EmployeeOutputDto employee;

    private Date startDate;

    private Date endDate;

    private Boolean approved;
}
