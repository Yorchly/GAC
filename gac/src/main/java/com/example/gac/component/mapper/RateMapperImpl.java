package com.example.gac.component.mapper;

import com.example.gac.model.Rate;
import com.example.gac.model.dto.RateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RateMapperImpl implements MapperComponent<RateDto, Rate> {
    @Override
    public Optional<RateDto> mapDaoToDto(Rate elementS)
    {
        if(Optional.ofNullable(elementS).isPresent())
            return Optional.of(new RateDto(elementS.getId(), elementS.getPrice(), elementS.getStartDate().toString(),
                    elementS.getEndDate().toString()));
        else
            return Optional.empty();
    }

    @Override
    public Optional<Rate> mapDtoToDao(RateDto elementT)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");
        if(Optional.ofNullable(elementT).isPresent())
            return Optional.of(new Rate(elementT.getId(), elementT.getPrice(),
                    LocalDate.parse(elementT.getStartDate(), formatter),
                    LocalDate.parse(elementT.getEndDate(), formatter)));
        else
            return Optional.empty();
    }

    @Override
    public List<RateDto> mapDaoToDto(List<Rate> elementS)
    {
        List<RateDto> list = new ArrayList<>();

        if(!elementS.isEmpty())
            for(Rate r: elementS)
                if(mapDaoToDto(r).isPresent())
                    list.add(mapDaoToDto(r).get());

        return list;
    }

    @Override
    public List<Rate> mapDtoToDao(List<RateDto> elementT)
    {
        List<Rate> list = new ArrayList<>();

        if(!elementT.isEmpty())
            for(RateDto r: elementT)
                if(mapDtoToDao(r).isPresent())
                    list.add(mapDtoToDao(r).get());

        return list;
    }
}
