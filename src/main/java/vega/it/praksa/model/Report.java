package vega.it.praksa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TeamMember teamMember;
    @ManyToOne
    private Project project;
    @ManyToOne
    private Category category;
    private Date date;
}
