package peaksoft.restaurantrest.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import peaksoft.restaurantrest.enums.Role;

import java.time.LocalDate;

@Data
public class UserRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank
    @Size(min = 5, message = "Пароль должен содержать минимум 5 символов")
    private String password;

    private String phoneNumber;

    @Past
    private LocalDate dateOfBirth;

    @Min(value = 0, message = "Стаж работы не может быть отрицательным")
    private int experience;

    @NotNull
    private Role role;

}
