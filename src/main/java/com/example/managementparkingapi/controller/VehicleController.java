package com.example.managementparkingapi.controller;

import com.example.managementparkingapi.dto.VehicleDto;
import com.example.managementparkingapi.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/cities/{id}/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable String id) {
        VehicleDto vehicleDtoTemporar=vehicleService.createVehicle(vehicleDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleDtoTemporar);
    }
}
