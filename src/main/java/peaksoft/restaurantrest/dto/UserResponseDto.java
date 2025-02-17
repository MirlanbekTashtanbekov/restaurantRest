package peaksoft.restaurantrest.dto;

import lombok.Data;
import peaksoft.restaurantrest.enums.Role;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;
    private int experience;
}
