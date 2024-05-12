package com.hotel.rating.services;

import com.hotel.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    // get all rating
    List<Rating> getAllRatings();

    // get all by userId
    List<Rating> getRatingsByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingsByHotelId(String hotelId);
}
