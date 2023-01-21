package com.example.managementparkingapi.controller;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable String id){
        CityDto cityDto= cityService.getCityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cityDto);
    }

    @GetMapping(params = {"code"})
    public ResponseEntity<CityDto> getCityByCode(@RequestParam String code){
        CityDto cityDto= cityService.getCityByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(cityDto);
    }

    @GetMapping()
    public ResponseEntity<List<CityDto>> getAllCities(){
        List<CityDto> cityDto= cityService.getAllCities();
        return ResponseEntity.status(HttpStatus.OK).body(cityDto);
    }
}
