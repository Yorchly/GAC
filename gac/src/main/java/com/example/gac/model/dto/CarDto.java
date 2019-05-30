package com.example.gac.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotNull(message = "El identificador no puede ser nulo.")
    private Integer id;
    private String carPlate;
    private String registrationYear;
}
