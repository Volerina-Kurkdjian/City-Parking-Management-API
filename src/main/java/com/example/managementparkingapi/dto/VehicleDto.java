package com.example.managementparkingapi.dto;

import com.example.managementparkingapi.model.ParkingFacility;
import com.example.managementparkingapi.model.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDto {

    private String id;
    @JsonIgnore
    private CityDto city;
    private VehicleType vehicleType;
    private Boolean isParked;
    @JsonIgnore
    private ParkingFacility parkingFacility;
}
