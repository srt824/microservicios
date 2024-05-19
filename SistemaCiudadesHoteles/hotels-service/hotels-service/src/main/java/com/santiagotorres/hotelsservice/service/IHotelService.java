package com.santiagotorres.hotelsservice.service;

import com.santiagotorres.hotelsservice.model.Hotel;

import java.util.List;

public interface IHotelService {

    public List<Hotel> getHotelsByCityId (Long city_id);
}
