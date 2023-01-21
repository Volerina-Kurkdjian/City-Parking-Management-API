package com.example.managementparkingapi.mapper;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {ParkingFacilityMapper.class})
public interface CityMapper {

     City map(CityDto cityDto);
     CityDto map(City city);
}
