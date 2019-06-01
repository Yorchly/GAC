package com.example.gac.model.repository;

import com.example.gac.model.Car;
import com.example.gac.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByBrand(String brand);

    List<Car> findAllByCarPlate(String carPlate);

    List<Car> findAllByRegistrationYear(LocalDate registrationYear);

    List<Car> findByColour(String colour);

    List<Car> findByRates(List<Rate> rates);

//    @Query("")
//    Optional<Car> findCarMoreProfitableInADate(String date);
}
