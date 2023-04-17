package com.humber.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.restaurants.document.MenuItem;
import com.humber.restaurants.document.Restaurant;
import com.humber.restaurants.repository.RestaurantRepository;

import java.util.*;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public void addRestaurant(Restaurant restaurant) {
    	if(0 == restaurant.getRestaurantId().length())
    		restaurant.setRestaurantId(UUID.randomUUID().toString());
        restaurantRepository.save(restaurant);
    }

    public Optional<Restaurant> getRestaurantById(String restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }
    
    public boolean updateRestaurant(String restaurantId, Restaurant restaurant) {
    	Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()) {
            restaurantRepository.save(restaurant);
            return true;
        }
        return false;
    }

    public void deleteRestaurant(String restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    public Set<MenuItem> getAllMenuItems(String restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            return restaurant.getMenu_list();
        }
        return Collections.<MenuItem>emptySet();
    }

    public boolean addMenuItemInRestaurant(String restaurantId, MenuItem menuItem) {
    	
    	if(0 == menuItem.getMenu_id().length())
    		menuItem.setMenu_id(UUID.randomUUID().toString());
    		
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.AddMenuItem(menuItem);
            restaurantRepository.save(restaurant);
            return true;
        }
        return false;
    }

    public Optional<MenuItem> getMenuItem(String restaurantId, String menuItemId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            return restaurant.getMenu_list().stream().findFirst();
        }
        return Optional.empty();
    }

    public boolean modifyMenuItem(String restaurantId, MenuItem menuItem) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            boolean isFound = restaurant.getMenu_list().removeIf(menu -> menu.getMenu_id().equals(menuItem.getMenu_id()));
            if (isFound) {
                restaurant.AddMenuItem(menuItem);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMenuItem(String restaurantId, String menuItemId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            boolean isDeleted = restaurant.getMenu_list().removeIf(menuItem -> menuItem.getMenu_id().equals(menuItemId));
            if (isDeleted) {
                restaurantRepository.save(restaurant);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}
