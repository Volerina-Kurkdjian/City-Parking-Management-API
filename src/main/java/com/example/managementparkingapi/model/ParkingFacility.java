package com.example.managementparkingapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class ParkingFacility {

    @Column(name="parking_facilities_type")
    private ParkingFacilityType type;
    @Id
    @NotBlank
    private String id;
    @Column(name="name",length = 256)
    @Max(value=256,message = "Name can not have more than 256 characters!")
    private String name;
    @JoinColumn(name="city")
    @ManyToOne
    private City city;
    @Column(name="capacity")
    @Positive
    private Integer capacity;
    @Column(name="available_capacity")
    private Integer availableCapacity;

    @OneToMany
    private List<Vehicle> vehicle;
}
