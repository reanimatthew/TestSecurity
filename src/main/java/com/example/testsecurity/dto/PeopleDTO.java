package com.example.testsecurity.dto;

import com.example.testsecurity.model.City;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PeopleDTO {
    String name;
    String surname;
    String passport;
    City city;
}
