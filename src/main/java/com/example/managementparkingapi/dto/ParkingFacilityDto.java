package com.example.managementparkingapi.dto;


import com.example.managementparkingapi.model.ParkingFacilityType;
import com.example.managementparkingapi.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingFacilityDto {

    private ParkingFacilityType type;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String name;

    @JsonIgnore
    private CityDto city;

    private Integer capacity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer availableCapacity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Vehicle> vehicle;
}
