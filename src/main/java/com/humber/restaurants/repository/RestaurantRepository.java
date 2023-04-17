package com.humber.restaurants.repository;

import com.humber.restaurants.document.Restaurant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
	
	public Optional<Restaurant> findByRestaurantId(String restaurantId);
	
	Long deleteByRestaurantId(String restaurantId);
}
