package com.example.gac.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {
    @NotNull(message = "El identificador no puede ser nulo.")
    private Integer id;
    private String dni;
    private String name;
}
