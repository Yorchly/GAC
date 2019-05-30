package com.example.gac.model;

import lombok.Data;

import javax.persistence.*;
//import java.sql.Timestamp;


@Data
@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String startDate;

    private String endDate;

    private Double rentPrice;

    @ManyToOne Client client;

    @ManyToOne Car car;
}
