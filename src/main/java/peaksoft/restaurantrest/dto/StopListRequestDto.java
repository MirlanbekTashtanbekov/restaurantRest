package peaksoft.restaurantrest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StopListRequestDto {

    @NotNull
    private Long menuItemId;

    @NotBlank
    private String reason;

    @NotNull
    private LocalDate date;
}
