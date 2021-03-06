package vega.it.praksa.model.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class WorkInputDto {
    private Long id;
    @NotNull private Long project;
    @NotNull private Long category;
    @NotBlank private String description;
    @NotNull private Double time;
    @NotNull private Double overtime;
    @NotNull private Long employee;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date date;
}
