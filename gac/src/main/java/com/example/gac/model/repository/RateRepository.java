package com.example.gac.model.repository;

import com.example.gac.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
    List<Rate> findAllByStartDate(String startDate);

    List<Rate> findAllByEndDate(String endDate);

    List<Rate> findAllByStartDateBetween(String startDate, String endDate);

    List<Rate> findAllByEndDateBetween(String startDate, String endDate);

    List<Rate> findAllByPrice(Double price);
}
