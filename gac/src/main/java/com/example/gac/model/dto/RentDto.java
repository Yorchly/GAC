package com.example.gac.model.dto;

import com.example.gac.model.Car;
import com.example.gac.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    @NotNull(message = "El identificador no puede ser nulo.")
    private Integer id;
    private CarDto car;
    private ClientDto client;
    private Double price;
    private String startDate;
    private String endDate;
}
