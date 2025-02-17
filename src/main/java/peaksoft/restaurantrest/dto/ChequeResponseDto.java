package peaksoft.restaurantrest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ChequeResponseDto {

    private Long id;
    private String waiterFullName;
    private List<String> menuItemNames;
    private double averagePrice;
    private double servicePercent;
    private double grandTotal;
    private LocalDate createdAt;

}
