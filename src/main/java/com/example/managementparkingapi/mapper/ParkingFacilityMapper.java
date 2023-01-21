package com.example.managementparkingapi.mapper;

import com.example.managementparkingapi.dto.ParkingFacilityDto;
import com.example.managementparkingapi.model.ParkingFacility;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingFacilityMapper {

    ParkingFacility map(ParkingFacilityDto parkingFacilityDto);
    ParkingFacilityDto map(ParkingFacility parkingFacility);
}
