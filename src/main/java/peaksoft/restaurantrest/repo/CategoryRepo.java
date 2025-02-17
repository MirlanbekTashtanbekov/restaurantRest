package peaksoft.restaurantrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantrest.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {


}
