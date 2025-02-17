package peaksoft.restaurantrest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantrest.dto.UserRequestDto;
import peaksoft.restaurantrest.dto.UserResponseDto;
import peaksoft.restaurantrest.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserApi {

    private final UserService userService;

    @PostMapping("/addUser")
    public UserResponseDto createUser(@RequestBody UserRequestDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }



    // Подать заявку
    @PostMapping("/apply")
    public UserResponseDto applyForJob(@RequestBody UserRequestDto dto) {
        return userService.applyForJob(dto);
    }

    // Одобрить заявку
    @PostMapping("/approve/{userId}/restaurant/{restaurantId}")
    public UserResponseDto approveUser(@PathVariable Long userId, @PathVariable Long restaurantId) throws Throwable {
        return userService.approveUser(userId, restaurantId);
    }

    // Отклонить заявку
    @DeleteMapping("/reject/{userId}")
    public void rejectUser(@PathVariable Long userId) {
        userService.rejectUser(userId);
    }

}
