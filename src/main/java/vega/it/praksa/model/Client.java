package vega.it.praksa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String zipCode;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;
}
