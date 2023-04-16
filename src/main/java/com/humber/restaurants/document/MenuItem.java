package com.humber.restaurants.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    //@GeneratedValue(strategy = GenerationType.UUID)
    private String menu_id = UUID.randomUUID().toString();
    private String menu_name;
    private String menu_category;
    private String menu_image;
    private double menu_price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Double.compare(menuItem.menu_price, menu_price) == 0 && Objects.equals(menu_name, menuItem.menu_name) && Objects.equals(menu_category, menuItem.menu_category) && Objects.equals(menu_image, menuItem.menu_image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu_name);
    }
}
