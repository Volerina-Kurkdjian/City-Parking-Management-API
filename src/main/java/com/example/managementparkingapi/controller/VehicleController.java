package com.example.managementparkingapi.controller;

import com.example.managementparkingapi.dto.ParkingFacilityDto;
import com.example.managementparkingapi.dto.VehicleDto;
import com.example.managementparkingapi.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/cities/{id}/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable String id) {
        VehicleDto vehicleDtoTemporar=vehicleService.createVehicle(vehicleDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleDtoTemporar);
    }

    @GetMapping(value = "/cities/{code}/vehicles")
    public ResponseEntity<List<VehicleDto>> getVehiclesByCityCode(@PathVariable String code){
        List<VehicleDto> vehicles= vehicleService.getVehiclesByCityCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @PutMapping("/parkingFacilities/{parkingId}/vehicle/{vehicleId}")
    public ResponseEntity<ParkingFacilityDto> parkVehicle(@PathVariable String parkingId,@PathVariable String vehicleId){
        ParkingFacilityDto parkedVehicleDto= vehicleService.parkVehicle(parkingId,vehicleId);
        return ResponseEntity.status(HttpStatus.OK).body(parkedVehicleDto);
    }

    @PutMapping("/vehicle/{vehicleId}")
    public ResponseEntity<ParkingFacilityDto> unparkVehicle(@PathVariable String vehicleId){
        ParkingFacilityDto parkedVehicleDto= vehicleService.unparkVehicle(vehicleId);
        return ResponseEntity.status(HttpStatus.OK).body(parkedVehicleDto);
    }

}
