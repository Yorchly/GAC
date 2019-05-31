package com.example.gac.service;

import com.example.gac.model.Rent;
import com.example.gac.model.dto.RentDto;
import com.example.gac.model.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
