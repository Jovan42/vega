package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class ReportDto {
    private Long id;
    private TeamMemberDto teamMember;
    private ProjectDto project;
    private CategoryDto category;
    private Date date;
}
