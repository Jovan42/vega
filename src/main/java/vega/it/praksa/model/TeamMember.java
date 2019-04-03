package vega.it.praksa.model;

import lombok.Data;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;

import javax.persistence.*;

@Entity
@Data
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double hoursPerWeek;
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private Status status;
}
