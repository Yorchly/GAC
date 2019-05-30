package com.example.gac.component.mapper;

import com.example.gac.model.Car;
import com.example.gac.model.dto.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarMapperImpl implements MapperComponent<CarDto, Car> {

    @Override
    public Optional<CarDto> mapDaoToDto(Car elementS) {
        return Optional.empty();
    }

    @Override
    public Optional<Car> mapDtoToDao(CarDto elementT) {
        return Optional.empty();
    }

    @Override
    public List<CarDto> mapDaoToDto(List<Car> elementS) {
        return null;
    }

    @Override
    public List<Car> mapDtoToDao(List<CarDto> elementT) {
        return null;
    }
}
