package com.example.managementparkingapi.mapper;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.dto.VehicleDto;
import com.example.managementparkingapi.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = CityDto.class)
public interface VehicleMapper {

    Vehicle map(VehicleDto vehicleDto);
    VehicleDto map(Vehicle vehicle);
}
