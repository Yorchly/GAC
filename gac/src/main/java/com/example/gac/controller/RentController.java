package com.example.gac.controller;

import com.example.gac.component.mapper.RentMapperImpl;
import com.example.gac.model.dto.RentDto;
import com.example.gac.service.RentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rent")
public class RentController
{
    @Autowired private RentMapperImpl mapper;

    @Autowired private RentServiceImpl service;

    @GetMapping
    public List<RentDto> get()
    {
        return mapper.mapDaoToDto(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentDto> findOne(@PathVariable("id") Integer id)
    {
        return  service.findOne(id)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RentDto> create(@RequestBody RentDto rentDto)
    {
        return Optional.ofNullable(rentDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<RentDto> update(@RequestBody RentDto rentDto)
    {
        if(mapper.mapDtoToDao(rentDto).isPresent())
            return service.update(mapper.mapDtoToDao(rentDto).get());
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<RentDto> delete(@RequestBody RentDto rentDto)
    {
        if(mapper.mapDtoToDao(rentDto).isPresent())
            return service.delete(mapper.mapDtoToDao(rentDto).get());
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
