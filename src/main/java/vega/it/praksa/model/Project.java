package vega.it.praksa.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Employee lead;
    @OneToMany(mappedBy = "project")
    List<ProjectMember> projectMembers;

}
