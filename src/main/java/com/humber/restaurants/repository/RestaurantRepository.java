package com.humber.restaurants.repository;

import com.humber.restaurants.document.Restaurant;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
	
}
