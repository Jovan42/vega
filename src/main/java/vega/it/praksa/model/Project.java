package vega.it.praksa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Client client;
    @ManyToOne
    private TeamMember lead;

}
