package vega.it.praksa.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double time;

    @Column(nullable = false)
    private Double overtime;

    @ManyToOne private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;
}
