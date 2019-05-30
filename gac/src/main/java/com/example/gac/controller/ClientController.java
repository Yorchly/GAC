package com.example.gac.controller;

import com.example.gac.component.mapper.ClientMapperImpl;
import com.example.gac.model.Client;
import com.example.gac.model.dto.ClientDto;
import com.example.gac.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Client> list = service.findAll();
        if(!list.isEmpty())
            return mapper.mapDaoToDto(list);
        else
            throw new ValidationException("Error al obtener la lista con todos los clientes");
    }

    @GetMapping("/{id}")
    public ClientDto findOne(@PathVariable("id") Integer id)
    {
        return Optional.ofNullable(id)
                .flatMap(service::findOne)
                .flatMap(mapper::mapDaoToDto)
                .orElseThrow(() -> new ValidationException("Error al obtener cliente con la id "+id));
    }


    @PostMapping
    public ClientDto create(@RequestBody ClientDto clientDto)
    {
        return Optional.ofNullable(clientDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .orElseThrow(() -> new ValidationException("Error al crear el cliente"));

    }

    @PutMapping
    public ClientDto update(@RequestBody ClientDto clientDto)
    {
        return Optional.ofNullable(clientDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::update)
                .flatMap(mapper::mapDaoToDto)
                .orElseThrow(() -> new ValidationException("Error al actualizar el cliente"));
    }


    @DeleteMapping
    public ClientDto delete(@RequestBody ClientDto clientDto)
    {
        return Optional.ofNullable(clientDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::delete)
                .flatMap(mapper::mapDaoToDto)
                .orElseThrow(() -> new ValidationException("Error al borrar el cliente"));
    }
}
