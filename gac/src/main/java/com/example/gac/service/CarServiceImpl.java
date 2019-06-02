package com.example.gac.service;

import com.example.gac.model.Car;
import com.example.gac.model.Rate;
import com.example.gac.model.dto.CarDto;
import com.example.gac.model.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired private CarRepository repository;

    @Autowired private RateServiceImpl rateService;

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

    @Override
    public ResponseEntity<CarDto> relateCarAndRate(Integer idCar, Integer idRate)
    {
        // Si cualquiera de los dos es nulo no se puede hacer la relacion por lo que se devuelve un error.
        if(!repository.findById(idCar).isPresent() || !rateService.findOne(idRate).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
        {
            Car car = repository.findById(idCar).get();
            Rate rate = rateService.findOne(idRate).get();
            boolean found = false;

            // Busca si hay algun alquiler ya vinculado con ese coche en la misma fecha o si el mismo alquiler que se
            // está pasando ya está vinculado al coche, si es asi da un error.
            for(Rate r: car.getRates())
                if(r.getStartDate().equals(rate.getStartDate()) || r.equals(rate))
                {
                    found = true;
                    break;
                }

            if(found)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else
            {
                // La relación es bidireccional asi que solo hace falta guardarlo en uno de los extremos.
                rate.getCars().add(car);
                rateService.create(rate);

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
    }
}
