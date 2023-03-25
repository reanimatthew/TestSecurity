package com.example.testsecurity.service;

import com.example.testsecurity.model.City;
import com.example.testsecurity.repo.CityRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CityService {
    CityRepo cityRepo;

    public List<City> getAll() {
        return cityRepo.findAll();
    }

    public City getCityByPopulation(BigInteger population) {
        return cityRepo.findCityByPopulation(population).orElseThrow(RuntimeException::new);
    }

    public City save(City city) {
        return cityRepo.save(city);
    }
}
