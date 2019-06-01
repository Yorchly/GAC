package com.example.gac.controller;

import com.example.gac.component.mapper.ClientMapperImpl;
import com.example.gac.model.dto.ClientDto;
import com.example.gac.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    // Mapper se mantiene en el controlador, es en el controlador donde se trabaja con dto mientras
    // que en los servicios se trabaja con Entities ya que es la lógica de negocios.
    @Autowired private ClientMapperImpl mapper;

    @Autowired private ClientServiceImpl service;

    @GetMapping
    public Page<ClientDto> get(Pageable pageable,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "dni", required = false) String dni)
    {
        List<ClientDto> list;
        if(Optional.ofNullable(name).isPresent())
            list = mapper.mapDaoToDto(service.findAllByName(name));
        else if(Optional.ofNullable(dni).isPresent())
            list = mapper.mapDaoToDto(service.findAllByDni(dni));
        else
            list = mapper.mapDaoToDto(service.findAll());

        return new PageImpl<>(list, pageable, list.size());
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
