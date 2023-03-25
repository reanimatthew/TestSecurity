package com.example.testsecurity.api;

import com.example.testsecurity.dto.PeopleDTO;
import com.example.testsecurity.mapper.PeopleMapper;
import com.example.testsecurity.model.People;
import com.example.testsecurity.service.PeopleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/peoples")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PeopleAPI {
    PeopleService peopleService;
    PeopleMapper peopleMapper;

    @GetMapping
    public List<PeopleDTO> getAll() {
        List<People> peopleList = peopleService.getAll();
        return peopleMapper.mapToListDTO(peopleList);
    }

}
