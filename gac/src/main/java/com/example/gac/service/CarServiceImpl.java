package com.example.gac.service;

import com.example.gac.model.Car;
import com.example.gac.model.dto.CarDto;
import com.example.gac.model.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired CarRepository repository;

    @Override
    public Optional<Car> create(Car car) {
        return Optional.ofNullable(repository.save(car));
    }

    @Override
    public ResponseEntity<CarDto> update(Car car) {
        // Controla que no se pueda actualizar un elemento que no está en la base de datos ya que
        // el save lo crearía.
        if(repository.findById(car.getId()).isPresent())
        {
            repository.save(car);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<CarDto> delete(Car car) {
        if(repository.findById(car.getId()).isPresent())
        {
            repository.delete(car);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<Car> findOne(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Car> findAllByCarPlate(String carPlate) {
        return repository.findAllByCarPlate(carPlate);
    }

    @Override
    public List<Car> findAllByRegistrationYear(LocalDate registrationYear) {
        return repository.findAllByRegistrationYear(registrationYear);
    }
}
