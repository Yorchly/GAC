package com.example.gac.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    private Integer id;
    private Double price;
    private String startDate;
    private String endDate;
}
