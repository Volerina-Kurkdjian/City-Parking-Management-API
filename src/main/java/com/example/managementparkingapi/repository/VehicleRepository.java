package com.example.managementparkingapi.repository;

import com.example.managementparkingapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {

    @Query(value = "SELECT v FROM Vehicle v WHERE v.city.id = :cityId")
    List<Vehicle> findAllByCityId(String cityId);
}
