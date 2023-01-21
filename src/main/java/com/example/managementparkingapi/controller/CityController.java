package com.example.managementparkingapi.controller;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cities")
public class CityController {

    private final CityService cityService;

    @PostMapping()
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto request){

       CityDto cityDto= cityService.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
    }
}
