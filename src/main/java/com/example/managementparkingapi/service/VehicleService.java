package com.example.managementparkingapi.service;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.dto.ParkingFacilityDto;
import com.example.managementparkingapi.dto.VehicleDto;
import com.example.managementparkingapi.mapper.ParkingFacilityMapper;
import com.example.managementparkingapi.mapper.VehicleMapper;
import com.example.managementparkingapi.model.ParkingFacility;
import com.example.managementparkingapi.model.Vehicle;
import com.example.managementparkingapi.repository.ParkingFacilityRepository;
import com.example.managementparkingapi.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final CityService cityService;
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final ParkingFacilityService parkingFacilityService;
    private final ParkingFacilityRepository parkingFacilityRepository;
    private final ParkingFacilityMapper parkingFacilityMapper;


    public VehicleDto createVehicle(VehicleDto vehicleDto, String id) {
        CityDto cityDto= cityService.getCityById(id);
        if(vehicleRepository.existsById(vehicleDto.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"The vehicle already exists!");
        }
        vehicleDto.setIsParked(false);
        vehicleDto.setCity(cityDto);
        return vehicleMapper.map(vehicleRepository.save(vehicleMapper.map(vehicleDto)));
    }


    public List<VehicleDto> getVehiclesByCityCode(String code) {
        CityDto cityDto=cityService.getCityByCode(code);
        List<ParkingFacilityDto> parkingList= cityDto.getParkingFacilitiesList();
        List<VehicleDto> vehicleList=new ArrayList<>();
        for(ParkingFacilityDto parking : parkingList){
           vehicleList.addAll( parking.getVehicle());
        }
        if(vehicleList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The city does not contain vehicles!");
        }
        return vehicleList;
    }


    public ParkingFacilityDto parkVehicle(String parkingId, String vehicleId) {
        Integer withoutCapacity=0;
        ParkingFacilityDto parkingFacilityDto=parkingFacilityService.getParkingFacilityById(parkingId);
        Vehicle vehicle=vehicleRepository.findById(vehicleId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(withoutCapacity.equals(parkingFacilityDto.getAvailableCapacity())){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"The parking facility is full! Can not park car!");
        }
        ParkingFacility parkingFacility= parkingFacilityMapper.map(parkingFacilityDto);
        vehicle.setIsParked(true);
        vehicle.setParkingFacility(parkingFacility);
        vehicleRepository.save(vehicle);
        List<Vehicle> vehicleList= parkingFacility.getVehicle();
        vehicleList.add(vehicle);
        parkingFacility.setAvailableCapacity(parkingFacility.getAvailableCapacity()-1);
        parkingFacilityRepository.save(parkingFacility);
        return  parkingFacilityMapper.map(parkingFacility);
    }
}
