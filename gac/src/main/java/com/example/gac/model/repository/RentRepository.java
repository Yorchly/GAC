package com.example.gac.model.repository;

import com.example.gac.model.Car;
import com.example.gac.model.Client;
import com.example.gac.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
    List<Rent> findAllByStartDate(String startDate);

    List<Rent> findAllByEndDate(String endDate);

    List<Rent> findAllByStartDateBetween(String startDate, String endDate);

    List<Rent> findAllByEndDateBetween(String startDate, String endDate);

    List<Rent> findAllByRentPrice(Double price);

    List<Rent> findAllByClient(Client client);

    List<Rent> findAllByCar(Car car);
}
