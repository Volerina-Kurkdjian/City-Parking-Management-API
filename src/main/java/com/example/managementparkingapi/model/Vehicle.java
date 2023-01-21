package com.example.managementparkingapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Vehicle {

    @Id
    @Column(name="id")
    private String id;

    @ManyToOne
    private City city;
    @Column(name="vehicle_type")
    private VehicleType vehicleType;
    @Column(name="isParked")
    private Boolean isParked;
    @ManyToOne
    private ParkingFacility parkingFacility;
}
