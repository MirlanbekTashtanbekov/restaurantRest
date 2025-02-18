package peaksoft.restaurantrest.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import peaksoft.restaurantrest.dto.UserRequestDto;
import peaksoft.restaurantrest.dto.UserResponseDto;
import peaksoft.restaurantrest.entities.Restaurant;
import peaksoft.restaurantrest.entities.User;
import peaksoft.restaurantrest.enums.RequestStatus;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.repo.RestaurantRepo;
import peaksoft.restaurantrest.repo.UserRepo;
import peaksoft.restaurantrest.service.UserService;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;

    public UserResponseDto createUser(UserRequestDto dto) {
        validateUser(dto);

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // пароли лучше шифровать
                .phoneNumber(dto.getPhoneNumber())
                .dateOfBirth(dto.getDateOfBirth())
                .experience(dto.getExperience())
                .role(dto.getRole())
                .build();

        userRepo.save(user);

        return mapToDto(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private void validateUser(UserRequestDto dto) {
        int age = Period.between(dto.getDateOfBirth(), LocalDate.now()).getYears();

        if (dto.getRole() == Role.CHEF) {
            if (age < 25 || age > 45) {
                throw new IllegalArgumentException("Возраст шеф-повара должен быть от 25 до 45 лет");
            }
            if (dto.getExperience() < 2) {
                throw new IllegalArgumentException("Стаж повара должен быть минимум 2 года");
            }
        } else if (dto.getRole() == Role.WAITER) {
            if (age < 18 || age > 30) {
                throw new IllegalArgumentException("Возраст официанта должен быть от 18 до 30 лет");
            }
            if (dto.getExperience() < 1) {
                throw new IllegalArgumentException("Стаж официанта должен быть минимум 1 год");
            }
        }
    }

    private UserResponseDto mapToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        dto.setExperience(user.getExperience());
        return dto;
    }

    @Override
    public UserResponseDto applyForJob(UserRequestDto dto) {
        validateUser(dto);

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .dateOfBirth(dto.getDateOfBirth())
                .experience(dto.getExperience())
                .role(dto.getRole())
                .status(RequestStatus.PENDING) // По умолчанию заявка на рассмотрении будет показывать в БД
                .build();

        userRepo.save(user);
        return mapToDto(user);
    }

    @Transactional
    public UserResponseDto approveUser(Long userId, Long restaurantId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

        if (user.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Эта заявка уже обработана");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));

        // Проверяем, есть ли сотрудники (чтобы не было null) если пусто
        if (restaurant.getUsers() == null) {
            restaurant.setUsers(new ArrayList<>());
        }

        if (restaurant.getUsers().size() >= 15) {
            throw new IllegalStateException("Вакансий нет");
        }

        user.setStatus(RequestStatus.APPROVED);
        user.setRestaurant(restaurant);
        restaurant.getUsers().add(user);

        return mapToDto(user);
    }

    @Transactional
    public void rejectUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

        if (user.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Эта заявка уже обработана");
        }

        userRepo.delete(user);
    }

    @Override
    public void validateUserEmail(UserRequestDto dto) {
        if (dto.getPassword().length() < 5) {
            throw new IllegalArgumentException("Пароль должен содержать минимум 5 символов");
        }

        if (!dto.getEmail().contains("@")) {
            throw new IllegalArgumentException("Некорректный email");
        }
    }


}
