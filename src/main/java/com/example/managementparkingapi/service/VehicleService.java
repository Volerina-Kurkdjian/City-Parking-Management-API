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
        CityDto cityDto = cityService.getCityById(id);
        if (vehicleRepository.existsById(vehicleDto.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The vehicle already exists!");
        }
        vehicleDto.setIsParked(false);
        vehicleDto.setCity(cityDto);
        return vehicleMapper.map(vehicleRepository.save(vehicleMapper.map(vehicleDto)));
    }


    public List<VehicleDto> getVehiclesByCityCode(String code) {
        CityDto cityDto = cityService.getCityByCode(code);
//        List<ParkingFacilityDto> parkingList= cityDto.getParkingFacilitiesList();
//        List<VehicleDto> vehicleList=new ArrayList<>();
//        for(ParkingFacilityDto parking : parkingList){
//           vehicleList.addAll( parking.getVehicle());
//        }
//        if(vehicleList.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The city does not contain vehicles!");
//        }
//        return vehicleList;

        return vehicleRepository.findAllByCityId(cityDto.getId())
                .stream()
                .map(vehicleMapper::map)
                .toList();
    }


    public ParkingFacilityDto parkVehicle(String parkingId, String vehicleId) {//trebuie sa folosim synchronized
        Integer withoutCapacity = 0;
        ParkingFacilityDto parkingFacilityDto = parkingFacilityService.getParkingFacilityById(parkingId);
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (withoutCapacity.equals(parkingFacilityDto.getAvailableCapacity())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The parking facility is full! Can not park car!");
        }
        ParkingFacility parkingFacility = parkingFacilityMapper.map(parkingFacilityDto);
        vehicle.setIsParked(true);
        vehicle.setParkingFacility(parkingFacility);
        vehicleRepository.save(vehicle);//annotare transitional pe entity
        var parkedVehicles = parkingFacility.getVehicle();
        if(parkedVehicles.stream().noneMatch(v -> v.getId().equals(vehicle.getId()))){
            parkedVehicles.add(vehicle);
            parkingFacility.setAvailableCapacity(parkingFacility.getAvailableCapacity() - 1);///here
        }
        parkingFacility.setVehicle(parkedVehicles);
        return parkingFacilityMapper.map(parkingFacilityRepository.save(parkingFacility));
    }

    public ParkingFacilityDto unparkVehicle(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!vehicle.getIsParked() || vehicle.getParkingFacility() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The car is not parked!");
        }
        ParkingFacilityDto parkingFacilityDto = parkingFacilityMapper.map(vehicle.getParkingFacility());
        var parkedVehicles = parkingFacilityDto.getVehicle()
                .stream()
                .filter(vehicleDto -> !vehicleDto.getId().equals(vehicle.getId()))
                .toList();
        parkingFacilityDto.setVehicle(parkedVehicles);
        parkingFacilityDto.setAvailableCapacity(parkingFacilityDto.getAvailableCapacity() + 1);

        vehicle.setIsParked(false);
        vehicle.setParkingFacility(null);
        vehicleRepository.save(vehicle);
        return parkingFacilityMapper
                .map(parkingFacilityRepository.save(parkingFacilityMapper.map(parkingFacilityDto)));
    }
}
