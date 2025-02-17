package peaksoft.restaurantrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantrest.entities.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

}
