package com.example.gac.component.mapper;

import com.example.gac.model.Car;
import com.example.gac.model.dto.CarDto;
import org.springframework.stereotype.Component;

// Usar LocalDate en vez de SimpleDateFormat
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarMapperImpl implements MapperComponent<CarDto, Car> {

    @Override
    public Optional<CarDto> mapDaoToDto(Car elementS)
    {
        if(Optional.ofNullable(elementS).isPresent())
            return Optional.of(new CarDto(elementS.getId(), elementS.getCarPlate(),
                    elementS.getRegistrationYear().toString()));
        else
            return  Optional.empty();
    }

    @Override
    public Optional<Car> mapDtoToDao(CarDto elementT)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if(Optional.ofNullable(elementT).isPresent())
            return Optional.of(new Car(elementT.getId(), elementT.getCarPlate(),
                    LocalDate.parse(elementT.getRegistrationYear(), formatter)));
        else
            return Optional.empty();
    }

    @Override
    public List<CarDto> mapDaoToDto(List<Car> elementS)
    {
        List<CarDto> list = new ArrayList<>();

        if(!elementS.isEmpty())
            for(Car c: elementS)
                if(mapDaoToDto(c).isPresent())
                    list.add(mapDaoToDto(c).get());

        return list;
    }

    @Override
    public List<Car> mapDtoToDao(List<CarDto> elementT)
    {
        List<Car> list = new ArrayList<>();

        if(!elementT.isEmpty())
            for(CarDto c: elementT)
                if(mapDtoToDao(c).isPresent())
                    list.add(mapDtoToDao(c).get());

        return list;
    }
}
