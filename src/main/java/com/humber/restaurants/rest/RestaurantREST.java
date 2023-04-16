package com.humber.restaurants.rest;

import com.humber.restaurants.document.MenuItem;
import com.humber.restaurants.document.Restaurant;
import com.humber.restaurants.dto.TokenDTO;
import com.humber.restaurants.repository.RestaurantRepository;
import com.humber.restaurants.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantREST {

	@Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurant() {
    	return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @PostMapping
    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return new ResponseEntity<String>("Added", HttpStatus.CREATED);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity getRestaurantById(@PathVariable String restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantService.getRestaurantById(restaurantId);

        if (restaurantOptional.isPresent()) {
            return new ResponseEntity<Restaurant>(restaurantOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/restaurants/{restaurantId}")
    public ResponseEntity<String> modifyRestaurant(@PathVariable String restaurantId, @RequestBody Restaurant restaurant) {
        boolean isUpdated = restaurantService.updateRestaurant(restaurantId, restaurant);
        if(isUpdated)
            return new ResponseEntity<String>("Restaurant Updated", HttpStatus.OK);
        return new ResponseEntity<String>("Restaurant not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable String restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<String>("Deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{restaurantId}/menuList")
    public Set<MenuItem> getAllMenuItems(@PathVariable String restaurantId) {
        return restaurantService.getAllMenuItems(restaurantId);
    }

    @PostMapping("/{restaurantId}/menuList")
    public ResponseEntity<String> addMenuItems(@PathVariable String restaurantId, @RequestBody MenuItem menuItem) {
        boolean isAdded = restaurantService.addMenuItemInRestaurant(restaurantId, menuItem);
        if(isAdded)
            return new ResponseEntity<String>("Added", HttpStatus.CREATED);

        return new ResponseEntity<String>("Restaurant Not found", HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}/menuList/{menuItemId}")
    public ResponseEntity getMenuItem(@PathVariable String restaurantId, @PathVariable String menuItemId) {
        Optional<MenuItem> optionalMenuItem = restaurantService.getMenuItem(restaurantId, menuItemId);
        if(optionalMenuItem.isPresent())
            return new ResponseEntity<MenuItem>(optionalMenuItem.get(), HttpStatus.OK);

        return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{restaurantId}/menuList/{menuItemId}")
    public ResponseEntity modifyMenuItem(@PathVariable String restaurantId, @PathVariable String menuItemId, @RequestBody MenuItem menuItem) {
        boolean isModified = restaurantService.modifyMenuItem(restaurantId, menuItem);
        if(isModified)
            return new ResponseEntity<String>("Updated menu", HttpStatus.OK);

        return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{restaurantId}/menuList/{menuItemId}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable String restaurantId, @PathVariable String menuItemId) {
        boolean isDeleted = restaurantService.deleteMenuItem(restaurantId, menuItemId);
        if(isDeleted)
            return new ResponseEntity<String>("Deleted", HttpStatus.NO_CONTENT);

        return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
    }
}
