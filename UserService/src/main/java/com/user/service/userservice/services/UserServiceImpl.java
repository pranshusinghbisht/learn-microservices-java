package com.user.service.userservice.services;

import com.user.service.userservice.entities.Hotel;
import com.user.service.userservice.entities.Rating;
import com.user.service.userservice.entities.User;
import com.user.service.userservice.exceptions.ResourceNotFoundException;
import com.user.service.userservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson; // Include Gson library
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {

        // get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server"));
        // i want rating also then
        // fetch rating of the above user from RATING SERVICE

        // http://localhost:8083/ratings/users/50a552cd-90f3-4d3a-8164-3872ae315d5e
        Rating[] ratingsByUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        // in prod localhost and port might change it should come with dynamic

        // Log the full ratings object using Gson
        Gson gson = new Gson();
        String jsonRatings = gson.toJson(ratingsByUser);
        logger.info("Ratings fetched for user: {}", jsonRatings);

        List<Rating> ratings = Arrays.stream(ratingsByUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel service


           ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            Hotel hotel = forEntity.getBody();
            logger.info("Hotel fetched for user: {}", hotel);

            // set the hotel to rating

            rating.setHotel(hotel);

            // return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
