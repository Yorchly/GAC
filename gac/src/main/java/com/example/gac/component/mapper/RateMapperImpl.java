package com.example.gac.component.mapper;

import com.example.gac.model.Rate;
import com.example.gac.model.dto.RateDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RateMapperImpl implements MapperComponent<RateDto, Rate> {
    @Override
    public Optional<RateDto> mapDaoToDto(Rate elementS) {
        return Optional.empty();
    }

    @Override
    public Optional<Rate> mapDtoToDao(RateDto elementT) {
        return Optional.empty();
    }

    @Override
    public List<RateDto> mapDaoToDto(List<Rate> elementS) {
        return null;
    }

    @Override
    public List<Rate> mapDtoToDao(List<RateDto> elementT) {
        return null;
    }
}
