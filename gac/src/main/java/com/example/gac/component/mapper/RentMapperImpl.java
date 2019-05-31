package com.example.gac.component.mapper;

import com.example.gac.model.Car;
import com.example.gac.model.Client;
import com.example.gac.model.Rent;
import com.example.gac.model.dto.CarDto;
import com.example.gac.model.dto.ClientDto;
import com.example.gac.model.dto.RentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RentMapperImpl implements MapperComponent<RentDto, Rent>{

    @Autowired private CarMapperImpl carMapper;

    @Autowired private ClientMapperImpl clientMapper;

    @Override
    public Optional<RentDto> mapDaoToDto(Rent elementS)
    {
        if(Optional.ofNullable(elementS).isPresent())
        {
            CarDto carDto = carMapper.mapDaoToDto(elementS.getCar()).get();
            ClientDto clientDto = clientMapper.mapDaoToDto(elementS.getClient()).get();

            return Optional.of(new RentDto(elementS.getId(), carDto , clientDto, elementS.getRentPrice(),
                    elementS.getStartDate().toString(), elementS.getEndDate().toString()));
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Rent> mapDtoToDao(RentDto elementT)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");
        if(Optional.ofNullable(elementT).isPresent()) {
            Car car = carMapper.mapDtoToDao(elementT.getCar()).get();
            Client client = clientMapper.mapDtoToDao(elementT.getClient()).get();

            return Optional.of(new Rent(elementT.getId(), LocalDate.parse(elementT.getStartDate(), formatter),
                    LocalDate.parse(elementT.getEndDate(), formatter), elementT.getPrice(), client,
                    car));
        }
        else
            return Optional.empty();
    }

    @Override
    public List<RentDto> mapDaoToDto(List<Rent> elementS)
    {
        List<RentDto> list = new ArrayList<>();

        if(!elementS.isEmpty())
            for(Rent r: elementS)
                if(mapDaoToDto(r).isPresent())
                    list.add(mapDaoToDto(r).get());

        return list;
    }

    @Override
    public List<Rent> mapDtoToDao(List<RentDto> elementT)
    {
        List<Rent> list = new ArrayList<>();

        if(!elementT.isEmpty())
            for(RentDto r: elementT)
                if(mapDtoToDao(r).isPresent())
                    list.add(mapDtoToDao(r).get());

        return list;
    }
}
