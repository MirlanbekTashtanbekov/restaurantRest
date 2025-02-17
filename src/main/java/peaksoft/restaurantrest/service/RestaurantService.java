package peaksoft.restaurantrest.service;

import org.apache.coyote.Response;
import peaksoft.restaurantrest.dto.RestaurantRequest;
import peaksoft.restaurantrest.entities.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantRequest restaurantRequest);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);
    void deleteRestaurant(Long id);

}
