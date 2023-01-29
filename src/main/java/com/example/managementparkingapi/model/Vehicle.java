package com.example.managementparkingapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
