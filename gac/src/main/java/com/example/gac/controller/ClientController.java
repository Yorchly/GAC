package com.example.gac.controller;

import com.example.gac.component.mapper.ClientMapperImpl;
import com.example.gac.model.Client;
import com.example.gac.model.dto.ClientDto;
import com.example.gac.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    // Mapper se mantiene en el controlador, es en el controlador donde se trabaja con dto mientras
    // que en los servicios se trabaja con Entities ya que es la lógica de negocios.
    @Autowired ClientMapperImpl mapper;

    @Autowired ClientServiceImpl service;

    @GetMapping
    public List<ClientDto> get()
    {
        return mapper.mapDaoToDto(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findOne(@PathVariable("id") Integer id)
    {
        return Optional.ofNullable(id)
                .flatMap(service::findOne)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto)
    {
        return Optional.ofNullable(clientDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());

    }

    @PutMapping
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto clientDto)
    {
        if(mapper.mapDtoToDao(clientDto).isPresent())
            return service.update(mapper.mapDtoToDao(clientDto).get());
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @DeleteMapping
    public ResponseEntity<ClientDto> delete(@RequestBody ClientDto clientDto)
    {
        if(mapper.mapDtoToDao(clientDto).isPresent())
            return service.delete(mapper.mapDtoToDao(clientDto).get());
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
