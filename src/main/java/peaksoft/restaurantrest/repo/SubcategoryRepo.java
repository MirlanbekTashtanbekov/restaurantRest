package peaksoft.restaurantrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantrest.entities.Subcategory;

import java.util.List;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findByCategoryIdOrderByName(Long categoryId);


}
