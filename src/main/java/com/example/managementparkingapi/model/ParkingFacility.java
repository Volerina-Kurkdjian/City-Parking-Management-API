package com.example.managementparkingapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingFacility {


    private ParkingFacilityType type;
    @Id
    @NotBlank
    private String id;
    @Column(name="name",length = 256,unique = true)
    @Length(max=256,message = "Name can not have more than 256 characters!")
    private String name;

    @ManyToOne
    private City city;
    @Column(name="capacity")
    @Positive
    private Integer capacity;
    @Column(name="available_capacity")
    private Integer availableCapacity;

    @OneToMany(mappedBy = "parkingFacility")
    private List<Vehicle> vehicle;

}
