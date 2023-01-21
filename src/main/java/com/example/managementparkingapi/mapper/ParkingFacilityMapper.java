package com.example.managementparkingapi.mapper;

import com.example.managementparkingapi.dto.ParkingFacilityDto;
import com.example.managementparkingapi.model.ParkingFacility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ParkingFacilityMapper {

    ParkingFacility map(ParkingFacilityDto parkingFacilityDto);

    @Mapping(target = "city", ignore = true)
    ParkingFacilityDto map(ParkingFacility parkingFacility);
}
