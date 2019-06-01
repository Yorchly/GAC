package com.example.gac.controller;

import com.example.gac.component.mapper.CarMapperImpl;
import com.example.gac.component.mapper.ClientMapperImpl;
import com.example.gac.component.mapper.RentMapperImpl;
import com.example.gac.model.dto.RentDto;
import com.example.gac.service.RentServiceImpl;
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
@RequestMapping("/rent")
public class RentController
{
    @Autowired private RentMapperImpl mapper;

    @Autowired private ClientMapperImpl clientMapper;

    @Autowired private CarMapperImpl carMapper;

    @Autowired private RentServiceImpl service;

    @GetMapping
    public Page<RentDto> get(Pageable pageable,
                             @RequestParam(value = "price", required = false) Double price,
                             @RequestParam(value = "startDate", required = false) String startDate,
                             @RequestParam(value = "endDate", required = false) String endDate)
    {
        List<RentDto> list;

        if(Optional.ofNullable(price).isPresent())
            list = mapper.mapDaoToDto(service.findAllByRentPrice(price));
        else if(Optional.ofNullable(startDate).isPresent())
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");
            list = mapper.mapDaoToDto(service.findAllByStartDate(LocalDate.parse(startDate, formatter)));
        }
        else if(Optional.ofNullable(endDate).isPresent())
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");
            list = mapper.mapDaoToDto(service.findAllByEndDate(LocalDate.parse(endDate, formatter)));
        }
        else
            list =  mapper.mapDaoToDto(service.findAll());

        // Transforma una lista en un objeto tipo Page.
        return new PageImpl<>(list, pageable, list.size());
    }

    @GetMapping("/client/{id}")
    public Page<RentDto> getAllRentForClient(@PathVariable("id") Integer id, Pageable pageable)
    {
        List<RentDto> list = mapper.mapDaoToDto(service.findAllByClient(id));
        return new PageImpl<>(list, pageable, list.size());
    }

    @GetMapping("/car/{id}")
    public Page<RentDto> getAllRentForCar(@PathVariable("id") Integer id, Pageable pageable)
    {
        List<RentDto> list = mapper.mapDaoToDto(service.findAllByCar(id));
        return new PageImpl<>(list, pageable, list.size());
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
