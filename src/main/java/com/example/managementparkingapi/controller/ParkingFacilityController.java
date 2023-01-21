package com.example.managementparkingapi.controller;


import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.dto.ParkingFacilityDto;
import com.example.managementparkingapi.service.ParkingFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkingFacilityController {

    public final ParkingFacilityService parkingFacilityService;
    @PostMapping("/cities/{id}/parkingFacilities")
    public ResponseEntity<ParkingFacilityDto> createParkingFacility(@RequestBody ParkingFacilityDto parkingFacility, @PathVariable String id) {
        ParkingFacilityDto parkingFacilityDto=parkingFacilityService.createParkingFacility(parkingFacility,id);
        return ResponseEntity.status(HttpStatus.OK).body(parkingFacilityDto);
    }

    @GetMapping("/parkingFacilities/{id}")
    public ResponseEntity<ParkingFacilityDto> getParkingFacilityById(@PathVariable String id){
        ParkingFacilityDto parkingFacilityDto= parkingFacilityService.getParkingFacilityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(parkingFacilityDto);
    }

    @GetMapping("/cities/{id}/parkingFacilities")
    public ResponseEntity<List<ParkingFacilityDto>> getParkingFacilities(@PathVariable String id) {
        List<ParkingFacilityDto> parkingFacilityDto=parkingFacilityService.getParkingFacilities(id);
        return ResponseEntity.status(HttpStatus.OK).body(parkingFacilityDto);
    }
}
