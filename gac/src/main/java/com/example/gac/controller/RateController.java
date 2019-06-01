package com.example.gac.controller;

import com.example.gac.component.mapper.RateMapperImpl;
import com.example.gac.model.dto.RateDto;
import com.example.gac.service.RateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rate")
public class RateController
{
    @Autowired private RateMapperImpl mapper;

    @Autowired private RateServiceImpl service;

    @GetMapping
    public List<RateDto> get()
    {
        return mapper.mapDaoToDto(service.findAll());
    }


    @GetMapping("{id}")
    public ResponseEntity<RateDto> findOne(@PathVariable("id") Integer id)
    {
        return Optional.ofNullable(id)
                .flatMap(service::findOne)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<RateDto> create(@RequestBody RateDto rateDto)
    {
        return Optional.ofNullable(rateDto)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
