package com.example.gac.service;

import com.example.gac.model.Car;
import com.example.gac.model.dto.CarDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CarService {
    /**
     * Función que crea un elemento del tipo Car.
     * @param car
     * @return
     */
    Optional<Car> create(Car car);

    /**
     * Función que actualiza un elemento del tipo Car.
     * @param car
     * @return
     */
    ResponseEntity<CarDto> update(Car car);

    /**
     * Función que elimina un elemento del tipo Car.
     * @param car
     * @return
     */
    ResponseEntity<CarDto> delete(Car car);

    /**
     * Busca un elemento del tipo Car por id.
     * @param id
     * @return
     */
    Optional<Car> findOne(Integer id);

    /**
     * Recupera todos los elementos del tipo Car que se encuentran en la base de datos.
     * @return
     */
    List<Car> findAll();
}
