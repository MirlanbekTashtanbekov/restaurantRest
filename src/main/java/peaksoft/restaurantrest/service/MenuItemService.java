package peaksoft.restaurantrest.service;

import peaksoft.restaurantrest.dto.MenuItemRequestDto;
import peaksoft.restaurantrest.dto.MenuItemResponseDto;
import peaksoft.restaurantrest.entities.MenuItem;
import peaksoft.restaurantrest.enums.Role;

import java.util.List;

public interface MenuItemService {

    MenuItemResponseDto createMenuItem(MenuItemRequestDto dto, Role userRole);
    List<MenuItemResponseDto> search(String query);
    List<MenuItemResponseDto> filterByVegetarian(boolean isVegetarian);
    List<MenuItemResponseDto> sortByPrice(boolean ascending);
    MenuItemResponseDto mapToDto(MenuItem menuItem);



}
