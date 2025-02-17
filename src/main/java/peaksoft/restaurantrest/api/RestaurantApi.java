package peaksoft.restaurantrest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantrest.dto.RestaurantRequest;
import peaksoft.restaurantrest.entities.Restaurant;
import peaksoft.restaurantrest.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
         restaurantService.createRestaurant(restaurantRequest);
        return ResponseEntity.ok("Restaurant created");
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
}
