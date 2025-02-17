package peaksoft.restaurantrest.service;

import peaksoft.restaurantrest.dto.UserRequestDto;
import peaksoft.restaurantrest.dto.UserResponseDto;
import peaksoft.restaurantrest.entities.User;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto dto);
    List<UserResponseDto> getAllUsers();

    private void validateUser(UserRequestDto dto) {
    }

    private UserResponseDto mapToDto(User user) {
        return null;
    }

    UserResponseDto applyForJob(UserRequestDto dto);
    UserResponseDto approveUser(Long userId, Long restaurantId) throws Throwable;
    void rejectUser(Long userId);
    void validateUserEmail(UserRequestDto dto);


}
