package com.example.gac.controller;

import com.example.gac.component.mapper.CarMapperImpl;
import com.example.gac.model.dto.CarDto;
import com.example.gac.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired private CarMapperImpl mapper;

    @Autowired private CarServiceImpl service;

    private LocalDate mapStringToDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");
        return LocalDate.parse(date, formatter);
    }

    @GetMapping
    public Page<CarDto> get(Pageable pageable,
                            @RequestParam(value = "carPlate", required = false) String carPlate,
                            @RequestParam(value = "registrationYear", required = false) String registrationYear)
    {
        List<CarDto> list;

        if(Optional.ofNullable(carPlate).isPresent())
            list = mapper.mapDaoToDto(service.findAllByCarPlate(carPlate));
        else if(Optional.ofNullable(registrationYear).isPresent())
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");
            list = mapper.mapDaoToDto(service.findAllByRegistrationYear(LocalDate.parse(registrationYear, formatter)));
        }
        else
            list = mapper.mapDaoToDto(service.findAll());

        return new PageImpl<>(list, pageable, list.size());
    }

    @GetMapping("{id}")
    public ResponseEntity<CarDto> findOne(@PathVariable("id") Integer id)
    {
        // Si obtiene correctamente el coche por la id devolvería un código
        // correcto (200). Si no devuelve un código Not Found (404)
        return service.findOne(id)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/moreProfitable")
    public ResponseEntity<CarDto> findCarMoreProfitableInADate(@RequestParam(value="startDate") String starDate,
                                                               @RequestParam(value="endDate") String endDate)
    {
        boolean badRequest = false;
        // Se inicializan a la fecha actual porque puede que nunca llegue a entrar en el if que los inicializa asi que
        // da error.
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();

        if(Optional.ofNullable(starDate).isPresent() && Optional.ofNullable(endDate).isPresent())
        {
            start = mapStringToDate(starDate);
            end = mapStringToDate(endDate);

            if(start.isAfter(end))
                badRequest = true;
        }
        else
            badRequest = true;

        if(badRequest)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return service.findCarMoreProfitableInADate(start, end).
                    flatMap(mapper::mapDaoToDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<CarDto> create(@RequestBody CarDto clientDto)
    {
        return Optional.ofNullable(clientDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());

    }

    @PostMapping("/{idCar}/rate/{idRate}")
    public ResponseEntity<CarDto> relateCarAndRate(@PathVariable("idCar") Integer idCar,
                                                   @PathVariable("idRate") Integer idRate)
    {
        return service.relateCarAndRate(idCar, idRate);
    }

    @PutMapping
    public ResponseEntity<CarDto> update(@RequestBody CarDto clientDto)
    {
        if(mapper.mapDtoToDao(clientDto).isPresent())
        {
            return service.update(mapper.mapDtoToDao(clientDto).get());
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping
    public ResponseEntity<CarDto> delete(@RequestBody CarDto clientDto)
    {
        if(mapper.mapDtoToDao(clientDto).isPresent())
        {
            return service.delete(mapper.mapDtoToDao(clientDto).get());
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
