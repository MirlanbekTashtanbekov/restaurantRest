package peaksoft.restaurantrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.restaurantrest.entities.User;
import peaksoft.restaurantrest.enums.Role;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByRole(Role role);

}
