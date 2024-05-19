package com.santiagotorres.citiesservice.service;

import com.santiagotorres.citiesservice.dto.CityDTO;

public interface ICityService {

    public CityDTO getCitiesHotels(String name, String country);
}
