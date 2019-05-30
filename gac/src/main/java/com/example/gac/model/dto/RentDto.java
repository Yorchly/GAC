package com.example.gac.model.dto;

import com.example.gac.model.Car;
import com.example.gac.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Integer id;
    private Car car;
    private Client client;
    private Double price;
    private String startDate;
    private String endDate;
}
