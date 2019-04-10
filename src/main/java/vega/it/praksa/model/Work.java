package vega.it.praksa.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double time;
    @Column(nullable = false)
    private Double overtime;
    @Temporal(TemporalType.DATE)

    private Date date;
}
