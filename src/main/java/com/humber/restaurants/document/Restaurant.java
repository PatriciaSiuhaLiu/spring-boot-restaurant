package com.humber.restaurants.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Restaurants")
public class Restaurant {
	@Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    private String restaurantId = UUID.randomUUID().toString();
    private String name;
    private String cuisine;
    private List<String> image = new ArrayList<String>();
    private String email;
    private String phone;
    private Address address;
    @Singular
    private Set<Review> reviews = new HashSet<Review>();
    private Set<MenuItem> menu_list = new HashSet<MenuItem>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(cuisine, that.cuisine) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cuisine, email, phone, address);
    }

    public void AddMenuItem(MenuItem menuItem) {
    	menu_list.add(menuItem);
    }
}
