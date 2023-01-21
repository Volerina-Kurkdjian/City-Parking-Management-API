package com.example.managementparkingapi.repository;

import com.example.managementparkingapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,String> {

    Boolean existsByCode(String code);

    Optional<City> findByCode(String code);
}
