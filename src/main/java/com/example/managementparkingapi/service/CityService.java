package com.example.managementparkingapi.service;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.mapper.CityMapper;
import com.example.managementparkingapi.model.City;
import com.example.managementparkingapi.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityMapper cityMapper;
    private final CityRepository cityRepository;
    public CityDto createCity(@NonNull CityDto request){

        if(cityRepository.existsByCode(request.getCode())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"The city with this code already exists!");
        }
        request.setId(UUID.randomUUID().toString());
        var city=cityMapper.map(request);
        return cityMapper.map( cityRepository.save(city));
    }


}
