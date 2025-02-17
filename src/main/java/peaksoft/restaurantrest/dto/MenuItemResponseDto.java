package peaksoft.restaurantrest.dto;

import lombok.Data;

@Data
public class MenuItemResponseDto {

    private Long id;
    private String name;
    private String imageUrl;
    private double price;
    private String description;
    private boolean isVegetarian;
    private String restaurantName;
    private String categoryName;
    private String subCategoryName;


    public void setSubcategoryName(String name) {

    }
}
