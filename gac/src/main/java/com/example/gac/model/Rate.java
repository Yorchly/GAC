package com.example.gac.model;

import lombok.Data;

import javax.persistence.*;
//import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String startDate;

    private String endDate;

    private Double price;

    @ManyToMany(fetch = FetchType.LAZY) Set<Car> cars = new HashSet<>();
}
