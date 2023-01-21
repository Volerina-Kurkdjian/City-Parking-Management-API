package com.example.managementparkingapi.service;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.dto.VehicleDto;
import com.example.managementparkingapi.mapper.VehicleMapper;
import com.example.managementparkingapi.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final CityService cityService;
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;


    public VehicleDto createVehicle(VehicleDto vehicleDto, String id) {
        CityDto cityDto= cityService.getCityById(id);
        if(vehicleRepository.existsById(vehicleDto.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"The vehicle already exists!");
        }
        vehicleDto.setIsParked(false);
        vehicleDto.setCity(cityDto);
        return vehicleMapper.map(vehicleRepository.save(vehicleMapper.map(vehicleDto)));
    }


}
