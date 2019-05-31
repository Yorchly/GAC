package com.example.gac.controller;

import com.example.gac.component.mapper.CarMapperImpl;
import com.example.gac.model.Car;
import com.example.gac.model.dto.CarDto;
import com.example.gac.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired CarMapperImpl mapper;

    @Autowired CarServiceImpl service;

    @GetMapping
    public List<CarDto> get()
    {
        return mapper.mapDaoToDto(service.findAll());
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


    @PostMapping
    public ResponseEntity<CarDto> create(@RequestBody CarDto clientDto)
    {
        return Optional.ofNullable(clientDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

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
