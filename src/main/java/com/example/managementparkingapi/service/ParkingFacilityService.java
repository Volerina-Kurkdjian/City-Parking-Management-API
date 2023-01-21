package com.example.managementparkingapi.service;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.dto.ParkingFacilityDto;
import com.example.managementparkingapi.mapper.CityMapper;
import com.example.managementparkingapi.mapper.ParkingFacilityMapper;
import com.example.managementparkingapi.model.ParkingFacility;
import com.example.managementparkingapi.repository.CityRepository;
import com.example.managementparkingapi.repository.ParkingFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingFacilityService {

    private final CityService cityService;
    private final ParkingFacilityRepository parkingFacilityRepository;

    private final ParkingFacilityMapper parkingFacilityMapper;

    public ParkingFacilityDto createParkingFacility(@NonNull ParkingFacilityDto parkingFacility,@NonNull String id) {
       CityDto cityDto= cityService.getCityById(id);
       for(ParkingFacilityDto parkingFacil: cityDto.getParkingFacilitiesList()){
           if(parkingFacility.getName().equalsIgnoreCase(parkingFacil.getName())){
               throw new ResponseStatusException(HttpStatus.CONFLICT,"The parking already exists in this city!");
           }
       }
       parkingFacility.setId(String.valueOf(UUID.randomUUID()));
       parkingFacility.setCity(cityDto);
       parkingFacility.setAvailableCapacity(parkingFacility.getCapacity());
       ParkingFacility parkingFacilityEntity= parkingFacilityRepository.save(parkingFacilityMapper.map(parkingFacility));
       return parkingFacilityMapper.map(parkingFacilityEntity);
    }


    public ParkingFacilityDto getParkingFacilityById(@NonNull String id) {
        return parkingFacilityMapper.map(parkingFacilityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public List<ParkingFacilityDto> getParkingFacilities(@NonNull String id) {
        return cityService.getCityById(id).getParkingFacilitiesList();
    }
}
