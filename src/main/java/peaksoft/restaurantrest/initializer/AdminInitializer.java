package peaksoft.restaurantrest.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import peaksoft.restaurantrest.entities.User;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.repo.UserRepo;

import java.time.LocalDate;


@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepo userRepo;

    public AdminInitializer(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {
        if (!userRepo.existsByRole(Role.ADMIN)) {
            User admin = User.builder()
                    .firstName("Adilet")
                    .lastName("Egemberdiev")
                    .email("adminAdi@restaurant.com")
                    .password("adminAdi123")
                    .phoneNumber("+996555112233")
                    .dateOfBirth(LocalDate.of(1998, 1, 1))
                    .experience(10)
                    .role(Role.ADMIN)
                    .build();

            userRepo.save(admin);
            System.out.println("Администратор назначен: adminAdi@restaurant.com / adminAdi123");
        }else {
            System.out.println("Администратор уже назначен");

        }

    }
}
