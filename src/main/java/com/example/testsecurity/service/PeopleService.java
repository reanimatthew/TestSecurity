package com.example.testsecurity.service;

import com.example.testsecurity.model.People;
import com.example.testsecurity.repo.PeopleRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PeopleService {
    PeopleRepo peopleRepo;

    public List<People> getAll() {
        return peopleRepo.findAll();
    }

    public People getByPassport(String passport) {
        return peopleRepo.findByPassport(passport).orElseThrow(RuntimeException::new);
    }

    public People save(People people) {
        return peopleRepo.save(people);
    }
}
