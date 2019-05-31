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
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    private Double price;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.LAZY) Set<Car> cars = new HashSet<>();
}
