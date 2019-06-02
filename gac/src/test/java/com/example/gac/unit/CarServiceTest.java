package com.example.gac.unit;

import com.example.gac.model.Car;
import com.example.gac.model.repository.CarRepository;
import com.example.gac.service.CarServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    // @InjectMocks se usa para lo que se va a probar, en este caso el service de Car.
    @InjectMocks CarServiceImpl service;

    // @Mock se usa para las dependencias que tenga lo que vas a probar. En este caso service depende de repository
    // para su correcto funcionamiento.
    @Mock CarRepository repository;

    @Test
    public void testIfServiceCreatesTheCar()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");

        // given
        final Car car = Mockito.mock(Car.class);
        car.setCarPlate("1234ABC");
        car.setRegistrationYear(LocalDate.parse("23-05-2019", formatter));

        final Car carStored = Mockito.mock(Car.class);
        carStored.setCarPlate("1234ABC");
        carStored.setRegistrationYear(LocalDate.parse("23-05-2019", formatter));

        // when
        Mockito.when(repository.save(car)).thenReturn(carStored);
        Car finalCar = service.create(car).get();

        // then
        Assert.assertNotNull(finalCar);
        Assert.assertEquals(carStored, finalCar);
    }

}
