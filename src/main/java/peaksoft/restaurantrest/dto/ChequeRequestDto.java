package peaksoft.restaurantrest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ChequeRequestDto {

    @NotNull
    private Long waiterId;

    @NotNull
    private List<Long> menuItemIds;

    @NotNull
    private double servicePercent;
}
