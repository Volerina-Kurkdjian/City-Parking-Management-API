package com.example.managementparkingapi.service;

import com.example.managementparkingapi.dto.CityDto;
import com.example.managementparkingapi.mapper.CityMapper;
import com.example.managementparkingapi.model.City;
import com.example.managementparkingapi.repository.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @InjectMocks
    private CityService cityService;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private CityMapper cityMapper;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {//will run before each test
        AutoCloseable autoCloseable=MockitoAnnotations.openMocks(this);
        cityService=new CityService(cityMapper,cityRepository);
    }

//    @AfterEach
//    void tearDown() throws Exception {//will close the resource after the test
//        autoCloseable.close();
//    }

    //Right-BICEP
    //Conformance
    @Test
    public void testCityCreation(){
        CityDto cityDto=new CityDto();
        cityDto.setName("Oradea");
        cityDto.setCode("24");
        cityService.createCity(cityDto);
        City city= cityMapper.map(cityDto);

        verify(cityRepository).save(city);
    }

    @Test
    public void testGettingCityById(){
        when(cityRepository.findById(anyString())).thenReturn(Optional.of(new City()));
        when(cityMapper.map(any(City.class))).thenReturn(new CityDto());
        var cityResult= cityService.getCityById("123");
        assertNotNull(cityResult);
    }

    @Test
    public void testGettingCityByCode(){
        when(cityRepository.findByCode(anyString())).thenReturn(Optional.of(new City()));
        when(cityMapper.map(any(City.class))).thenReturn(new CityDto());
        var cityResult=cityService.getCityByCode("1234");
        assertNotNull(cityResult);
    }

    @Test
    public void testGetAllCities(){
        List<City> cityList=new ArrayList<>();
        City city=new City("1234","Navodari","222",null);
        cityList.add(city);
        when(cityRepository.findAll()).thenReturn(cityList);
        var result=cityService.getAllCities();
        assertNotNull(result);
    }






















}