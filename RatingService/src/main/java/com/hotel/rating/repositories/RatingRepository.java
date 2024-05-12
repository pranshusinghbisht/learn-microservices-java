package com.hotel.rating.repositories;

import com.hotel.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    //custom finder method
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}
