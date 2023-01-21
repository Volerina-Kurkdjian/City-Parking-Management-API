package com.example.managementparkingapi.repository;

import com.example.managementparkingapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,String> {

    Boolean existsByCode(String code);

}
