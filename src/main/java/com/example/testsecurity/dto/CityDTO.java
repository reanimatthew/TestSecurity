package com.example.testsecurity.dto;

import com.example.testsecurity.model.People;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Builder
@Data
public class CityDTO {
    String cityName;
    BigInteger population;
    List<People> people;
}
