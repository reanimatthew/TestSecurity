package com.example.testsecurity.repo;

import com.example.testsecurity.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CityRepo extends JpaRepository<City, Long> {
    Optional<City> findCityByPopulation(BigInteger population);
}
