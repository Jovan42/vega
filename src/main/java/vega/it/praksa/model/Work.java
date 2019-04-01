package vega.it.praksa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Project project;
    @ManyToOne
    private Category category;
    private String description;
    private Double time;
    private Double overtime;
    @Temporal(TemporalType.DATE)
    private Date date;
}
