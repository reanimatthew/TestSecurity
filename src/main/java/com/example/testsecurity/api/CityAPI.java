package com.example.testsecurity.api;

import com.example.testsecurity.dto.CityDTO;
import com.example.testsecurity.mapper.CityMapper;
import com.example.testsecurity.model.City;
import com.example.testsecurity.service.CityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cities")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CityAPI{
    CityService cityService;
    CityMapper cityMapper;

    @GetMapping
    public List<CityDTO> getAll() {
        List<City> cityList = cityService.getAll();
        return cityMapper.mapToListDTO(cityList);
    }

}
