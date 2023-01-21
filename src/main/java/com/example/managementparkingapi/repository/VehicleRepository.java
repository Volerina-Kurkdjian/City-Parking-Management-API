package com.example.managementparkingapi.repository;

import com.example.managementparkingapi.model.City;
import com.example.managementparkingapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {


}
