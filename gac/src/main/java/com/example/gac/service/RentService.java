package com.example.gac.service;

import com.example.gac.model.Rent;
import com.example.gac.model.dto.RentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RentService {

    /**
     * Función que crea un elemento del tipo Rent.
     * @param rent
     * @return
     */
    Optional<Rent> create(Rent rent);

    /**
     * Función que actualiza un elemento del tipo Rent.
     * @param rent
     * @return
     */
    ResponseEntity<RentDto> update(Rent rent);

    /**
     * Función que elimina un elemento del tipo Rent.
     * @param rent
     * @return
     */
    ResponseEntity<RentDto> delete(Rent rent);

    /**
     * Busca un elemento del tipo Rent por id.
     * @param id
     * @return
     */
    Optional<Rent> findOne(Integer id);

    /**
     * Recupera todos los elementos del tipo Rent que se encuentran en la base de datos.
     * @return
     */
    List<Rent> findAll();
}
