package peaksoft.restaurantrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantrest.entities.Cheque;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface ChequeRepo extends JpaRepository<Cheque, Long> {

    List<Cheque> findByWaiterIdAndCreatedAt(Long waiterId, LocalDate date);

    List<Cheque> findByWaiterRestaurantIdAndCreatedAt(Long restaurantId, LocalDate date);
}
