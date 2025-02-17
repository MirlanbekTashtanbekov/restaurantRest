package peaksoft.restaurantrest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StopListResponseDto {
    private Long id;
    private String menuItemName;
    private LocalDate date;
    private String reason;
}
