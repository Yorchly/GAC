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

    List<Car> findAllByCarPlate(String carPlate);

    List<Car> findAllByRegistrationYear(LocalDate registrationYear);

    @Query(value = "select c from Car c join c.rents r where r.startDate between ?1 and ?2 order by r.rentPrice desc")
    List<Car> findCarMoreProfitableInADate(LocalDate startDate, LocalDate endDate);
}
