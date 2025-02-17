package peaksoft.restaurantrest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuItemRequestDto {

    @NotBlank
    private String name;

    private String imageUrl;

    @Min(value = 0, message = "Цена не должен быть отрицательным")
    private double price;

    private String description;

    private boolean isVegetarian;

    private Long restaurantId;
    private Long subcategoryId;



}
