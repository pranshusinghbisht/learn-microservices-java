package com.service.hotel.services;

import com.service.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create
    Hotel create(Hotel hotel);

    // getAll
    List<Hotel> getAll();


    // get Single
    Hotel get(String id);


}
