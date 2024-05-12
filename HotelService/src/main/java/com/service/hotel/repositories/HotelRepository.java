package com.service.hotel.repositories;

import com.service.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, String> {
}
