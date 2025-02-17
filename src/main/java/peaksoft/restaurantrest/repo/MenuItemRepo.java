package peaksoft.restaurantrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.restaurantrest.entities.MenuItem;

import java.util.List;

public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {

    @Query("SELECT m FROM MenuItem m WHERE " +
            "LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(m.subcategory.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(m.subcategory.category.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<MenuItem> search(@Param("query") String query);

    List<MenuItem> findByIsVegetarian(boolean isVegetarian);

    List<MenuItem> findAllByOrderByPriceAsc();
    List<MenuItem> findAllByOrderByPriceDesc();

}
