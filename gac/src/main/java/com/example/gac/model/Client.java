package com.example.gac.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String surname;

    private String dni;

    private String location;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY) private Set<Rent> rents = new HashSet<>();

    // Constructor para DTO -> DAO
    public Client(Integer id, String dni, String name)
    {
        this.id = id;
        this.dni = dni;
        this.name = name;
    }
}
