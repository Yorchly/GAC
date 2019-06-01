package com.example.gac.service;

import com.example.gac.model.Car;
import com.example.gac.model.Client;
import com.example.gac.model.Rent;
import com.example.gac.model.dto.RentDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
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

    /**
     * Encuentra todas las rentas coincidentes con la fecha de inicio pasada en el argumento.
     * @param startDate
     * @return
     */
    List<Rent> findAllByStartDate(LocalDate startDate);

    /**
     * Encuentra todas las rentas coincidentes con la fecha de fin pasada en el argumento.
     * @param endDate
     * @return
     */
    List<Rent> findAllByEndDate(LocalDate endDate);

    /**
     * Encuentra todas las rentas con un precio igual al que se le pasa como argumento.
     * @param price
     * @return
     */
    List<Rent> findAllByRentPrice(Double price);

    /**
     * Encuentra todas las rentas para un cliente.
     * @param clientId
     * @return
     */
    List<Rent> findAllByClient(Integer clientId);

    /**
     * Encuentra todas las rentas para un coche.
     * @param carId
     * @return
     */
    List<Rent> findAllByCar(Integer carId);


}
