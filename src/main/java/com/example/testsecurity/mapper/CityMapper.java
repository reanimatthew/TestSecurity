package com.example.testsecurity.mapper;

import com.example.testsecurity.dto.CityDTO;
import com.example.testsecurity.model.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityMapper {

    public List<CityDTO> mapToListDTO(List<City> cityList) {
        List<CityDTO> cityDTOList = new ArrayList<>();
        cityList.forEach(city -> cityDTOList.add(
                CityDTO.builder()
                        .cityName(city.getCityName())
                        .population(city.getPopulation())
                        .build()
        ));
        return cityDTOList;
    }
}
