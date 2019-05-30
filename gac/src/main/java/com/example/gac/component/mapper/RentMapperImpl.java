package com.example.gac.component.mapper;

import com.example.gac.model.Rent;
import com.example.gac.model.dto.RentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RentMapperImpl implements MapperComponent<RentDto, Rent>{
    @Override
    public Optional<RentDto> mapDaoToDto(Rent elementS) {
        return Optional.empty();
    }

    @Override
    public Optional<Rent> mapDtoToDao(RentDto elementT) {
        return Optional.empty();
    }

    @Override
    public List<RentDto> mapDaoToDto(List<Rent> elementS) {
        return null;
    }

    @Override
    public List<Rent> mapDtoToDao(List<RentDto> elementT) {
        return null;
    }
}
