package peaksoft.restaurantrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantrest.entities.StopList;

import java.time.LocalDate;
import java.util.Optional;

public interface StopListRepo extends JpaRepository<StopList, Long> {

    Optional<StopList> findByMenuItemIdAndDate(Long menuItemId, LocalDate date);

}
