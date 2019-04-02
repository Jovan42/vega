package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import vega.it.praksa.model.Work;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkListDto {
    private List<Work> works;
}
