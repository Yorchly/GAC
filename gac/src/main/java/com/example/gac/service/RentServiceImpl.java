package com.example.gac.service;

import com.example.gac.model.Car;
import com.example.gac.model.Client;
import com.example.gac.model.Rate;
import com.example.gac.model.Rent;
import com.example.gac.model.dto.RentDto;
import com.example.gac.model.repository.RentRepository;
import org.apache.tomcat.jni.Local;
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

    // Esta función comprueba si dos fechas son iguales. Cuando se saca de la base de datos la fecha aparece como que al
    // dia se le ha restado 1. A la hora de comparar es un problema.
    private boolean checkDates(LocalDate date1, LocalDate date2)
    {
        boolean iguales = false;
        if(date1.equals(date2) || (LocalDate.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth()+1)
                .equals(LocalDate.of(date2.getYear(), date2.getMonthValue(), date2.getDayOfMonth()))))
            iguales = true;

        return iguales;
    }

    @Override
    public Optional<Rent> create(Rent rent)
    {
        boolean returnEmpty = false;
        // Se comprueba que no se esté intentando actualizar mediante el metodo POST.
        if(repository.findById(rent.getId()).isPresent())
            returnEmpty = true;

        // Se comprueba si el coche ya está alquilado para la fecha de inicio.
        List<Rent> list = repository.findAll();
        for(Rent r: list)
            // Debido a que la relación está puesta como lazy lo que se obtiene al hacer un r.getCar() es un proxy,
            // no el objeto de la clase por lo que comparar usando equals no es la mejor opción. Comparar con la ID
            // debería ser suficiente ya que se supone única.
            if(r.getStartDate().plusDays(1).equals(rent.getStartDate()) &&
                    r.getCar().getId().equals(rent.getCar().getId()))
            {
                    returnEmpty = true;
                    break;
            }

        if(returnEmpty)
            return Optional.empty();
        else
        {
            // Comprueba si el coche enviado con rent no es nulo y si no está presente en la base de datos
            // lo crea.
            if(Optional.ofNullable(rent.getCar()).isPresent())
            {
                if (!carService.findOne(rent.getCar().getId()).isPresent())
                    carService.create(rent.getCar());

                // Si existe coge el precio de la tarifa que esté asignada al coche, si no coge el precio que se
                // le pasa como argumento en el DTO.
                // Se coge el precio de la tarifa en la que tanto en tarifa como en alquiler coincide la fecha de inicio.
                // Para que se obtenga correctamente el List que tiene todos los rates que están vinculados al coche se debe
                // usar service.
                Car car = carService.findOne(rent.getCar().getId()).get();
                for(Rate r: car.getRates())
                    if(r.getStartDate().equals(rent.getStartDate()))
                        rent.setRentPrice(r.getPrice());
            }

            // Realiza las mismas comprobaciones que arriba pero para cliente.
            if(Optional.ofNullable(rent.getClient()).isPresent())
                if (!clientService.findOne(rent.getClient().getId()).isPresent())
                    clientService.create(rent.getClient());

            return Optional.ofNullable(repository.save(rent));
        }

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
