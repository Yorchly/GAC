package com.example.gac.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    private String brand;

    private String carPlate;

    private LocalDate registrationYear;

    private String colour;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY) Set<Rent> rents = new HashSet<>();

    @ManyToMany(mappedBy = "cars", fetch = FetchType.LAZY) Set<Rate> rates = new HashSet<>();

    // Constructor para cuando se mapea el DTO a DAO
    public Car(Integer id, String carPlate, LocalDate registrationYear)
    {
        this.id = id;
        this.carPlate = carPlate;
        this.registrationYear = registrationYear;
    }
}
