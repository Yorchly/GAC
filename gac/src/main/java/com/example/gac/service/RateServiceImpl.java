package com.example.gac.service;

import com.example.gac.model.Rate;
import com.example.gac.model.dto.RateDto;
import com.example.gac.model.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired RateRepository repository;

    @Override
    public Optional<Rate> create(Rate rate)
    {
        if(repository.findById(rate.getId()).isPresent())
            return Optional.empty();
        else
            return Optional.ofNullable(repository.save(rate));
    }

    @Override
    public Optional<Rate> findOne(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Rate> findAll() {
        return repository.findAll();
    }
}
