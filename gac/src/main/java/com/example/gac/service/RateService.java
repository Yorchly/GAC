package com.example.gac.service;

import com.example.gac.model.Rate;
import com.example.gac.model.dto.RateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RateService {
    /**
     * Función que crea un elemento del tipo Rate.
     * @param rate
     * @return
     */
    Optional<Rate> create(Rate rate);

    /**
     * Busca un elemento del tipo Rate por id.
     * @param id
     * @return
     */
    Optional<Rate> findOne(Integer id);

    /**
     * Recupera todos los elementos del tipo Rate que se encuentran en la base de datos.
     * @return
     */
    List<Rate> findAll();
}
