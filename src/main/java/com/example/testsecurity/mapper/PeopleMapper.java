package com.example.testsecurity.mapper;


import com.example.testsecurity.dto.PeopleDTO;
import com.example.testsecurity.model.People;
import com.example.testsecurity.repo.PeopleRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleMapper {

    public List<PeopleDTO> mapToListDTO(List<People> peopleList) {
        List<PeopleDTO> peopleDTOList = new ArrayList<>();
        peopleList.forEach(people -> peopleDTOList.add(
                PeopleDTO.builder()
                        .name(people.getName())
                        .surname(people.getSurname())
                        .passport(people.getPassport())
                        .city(people.getCity())
                        .build()
        ));
        return peopleDTOList;
    }

}
