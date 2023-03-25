package com.example.testsecurity;

import com.example.testsecurity.mapper.PeopleMapper;
import com.example.testsecurity.model.City;
import com.example.testsecurity.model.People;
import com.example.testsecurity.repo.CityRepo;
import com.example.testsecurity.repo.PeopleRepo;
import com.example.testsecurity.service.CityService;
import com.example.testsecurity.service.PeopleService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DBFiller {
    CityRepo cityRepo;
    PeopleRepo peopleRepo;

    @PostConstruct
    public void init() {
        City moscow = createCity("Moscow", new BigInteger("20000000"));
        City beijing = createCity("Beijing", new BigInteger("30000000"));

        createPeople("Alex", "Ivanov", "4198393775", moscow);
        createPeople("Anna", "Morozova", "4823713341", moscow);
        createPeople("Chan", "Juan", "110101201701017234", beijing);
        createPeople("Dong", "Mei", "110101201701015319", beijing);

    }

    private City createCity(String cityName, BigInteger population) {
        City city = City.builder()
                .cityName(cityName)
                .population(population)
                .build();
        return cityRepo.save(city);
    }

    private People createPeople(String name, String surname, String passport, City city) {
        People people = People.builder()
                .name(name)
                .surname(surname)
                .passport(passport)
                .city(city)
                .build();
        return peopleRepo.save(people);
    }

}
