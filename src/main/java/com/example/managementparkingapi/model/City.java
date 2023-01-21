package com.example.managementparkingapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class City {

    @Id
    @NotBlank(message = "Cannot be blank!")
    private String id;
    @Column(name="name",length = 256)
    @Length(max=256,message = "Name can not have more than 256 characters!")
    private String name;
    @Column(name="code",length = 4,unique = true)
    @Length(max = 4,message = "Code should not be longer than 4 characters!")
    private String code;

    @OneToMany
    private List<ParkingFacility> parkingFacilitiesList;
}
