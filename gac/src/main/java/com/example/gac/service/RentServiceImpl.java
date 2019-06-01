package com.example.gac.service;

import com.example.gac.model.Car;
import com.example.gac.model.Client;
import com.example.gac.model.Rent;
import com.example.gac.model.dto.RentDto;
import com.example.gac.model.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {

    @Autowired private RentRepository repository;

    @Autowired private CarServiceImpl carService;

    @Autowired private ClientServiceImpl clientService;

    @Override
    public Optional<Rent> create(Rent rent)
    {
        // Comprueba si el coche enviado con rent no es nulo y si no está presente en la base de datos
        // lo crea.
        if(Optional.ofNullable(rent.getCar()).isPresent())
            if (!carService.findOne(rent.getCar().getId()).isPresent())
                carService.create(rent.getCar());

        // Realiza las mismas comprobaciones que arriba pero para cliente.
        if(Optional.ofNullable(rent.getClient()).isPresent())
            if (!clientService.findOne(rent.getClient().getId()).isPresent())
                clientService.create(rent.getClient());


        return Optional.ofNullable(repository.save(rent));
    }

    @Override
    public ResponseEntity<RentDto> update(Rent rent) {
        if(repository.findById(rent.getId()).isPresent())
        {
            // Comprueba si el coche enviado con rent no es nulo y si no está presente en la base de datos
            // lo crea.
            if(Optional.ofNullable(rent.getCar()).isPresent())
                if(!carService.findOne(rent.getCar().getId()).isPresent())
                    carService.create(rent.getCar());

            // Realiza las mismas comprobaciones que arriba pero para cliente.
            if(Optional.ofNullable(rent.getClient()).isPresent())
                if(!clientService.findOne(rent.getClient().getId()).isPresent())
                    clientService.create(rent.getClient());

            repository.save(rent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<RentDto> delete(Rent rent) {
        if(repository.findById(rent.getId()).isPresent())
        {
            repository.delete(rent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<Rent> findOne(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Rent> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Rent> findAllByStartDate(LocalDate startDate) {
        return repository.findAllByStartDate(startDate);
    }

    @Override
    public List<Rent> findAllByEndDate(LocalDate endDate) {
        return repository.findAllByEndDate(endDate);
    }

    @Override
    public List<Rent> findAllByRentPrice(Double price) {
        return repository.findAllByRentPrice(price);
    }

    @Override
    public List<Rent> findAllByClient(Integer clientId)
    {
        if(clientService.findOne(clientId).isPresent())
            return repository.findAllByClient(clientService.findOne(clientId).get());
        else
            return new ArrayList<>();
    }

    @Override
    public List<Rent> findAllByCar(Integer carId)
    {
        if(carService.findOne(carId).isPresent())
            return repository.findAllByCar(carService.findOne(carId).get());
        else
            return new ArrayList<>();
    }
}
