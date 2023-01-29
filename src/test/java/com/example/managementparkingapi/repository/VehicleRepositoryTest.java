package com.example.managementparkingapi.repository;

import com.example.managementparkingapi.model.City;
import com.example.managementparkingapi.model.ParkingFacility;
import com.example.managementparkingapi.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static com.example.managementparkingapi.model.ParkingFacilityType.CAR_PARK;
import static com.example.managementparkingapi.model.VehicleType.CAR;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ParkingFacilityRepository parkingFacilityRepository;

    @Test
     void itShouldFindAllVehiclesByCityId(){
        //given
        City city=new City("1","Bucuresti","1234",null);
        ParkingFacility parkingFacility=new ParkingFacility(CAR_PARK,"11","parcare",city,30,20,null);
        List<ParkingFacility> parkingFacilityList=new ArrayList<>();
//       city.setParkingFacilitiesList(parkingFacilityList);
        cityRepository.save(city);
        Vehicle vehicle=new Vehicle("1",city,CAR,true,parkingFacility);
//      List<Vehicle> vehicleList=new ArrayList<>();
//      vehicleList.add(vehicle);
//      parkingFacility.setVehicle(vehicleList);
        parkingFacilityRepository.save(parkingFacility);
        vehicleRepository.save(vehicle);


        //when
        List<Vehicle> checkedVehicles=vehicleRepository.findAllByCityId("1");

        //then
        assertTrue(!checkedVehicles.isEmpty());
    }
}
