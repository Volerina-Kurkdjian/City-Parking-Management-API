package com.example.managementparkingapi.dto;

import com.example.managementparkingapi.model.ParkingFacility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String name;
    private String code;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ParkingFacility> parkingFacilitiesList;
}
