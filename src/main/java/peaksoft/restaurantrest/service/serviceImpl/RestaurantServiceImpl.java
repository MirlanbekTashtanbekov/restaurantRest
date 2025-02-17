package peaksoft.restaurantrest.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantrest.dto.RestaurantRequest;
import peaksoft.restaurantrest.entities.Restaurant;
import peaksoft.restaurantrest.repo.RestaurantRepo;
import peaksoft.restaurantrest.service.RestaurantService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;



    @Override
    public Restaurant createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setNumberOfEmployees(restaurantRequest.getNumberOfEmployees());
        restaurant.setService(Integer.valueOf(restaurantRequest.getService()));
        return restaurantRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepo.findById(id).orElseThrow(()-> new RuntimeException("Ресторан не найден"));
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }
}
