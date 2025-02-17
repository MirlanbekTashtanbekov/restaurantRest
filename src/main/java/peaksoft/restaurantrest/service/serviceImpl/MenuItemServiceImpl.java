package peaksoft.restaurantrest.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.restaurantrest.dto.MenuItemRequestDto;
import peaksoft.restaurantrest.dto.MenuItemResponseDto;
import peaksoft.restaurantrest.entities.MenuItem;
import peaksoft.restaurantrest.entities.Restaurant;
import peaksoft.restaurantrest.entities.Subcategory;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.repo.MenuItemRepo;
import peaksoft.restaurantrest.repo.RestaurantRepo;
import peaksoft.restaurantrest.repo.SubcategoryRepo;
import peaksoft.restaurantrest.service.MenuItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepo menuItemRepo;

    private final RestaurantRepo restaurantRepo;

    private final SubcategoryRepo subcategoryRepo;


    @Override
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto dto, Role userRole) {
        if (userRole != Role.ADMIN && userRole != Role.CHEF) {
            throw new IllegalArgumentException("Только админ и повар могут добавлять блюда!");
        }

        Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));

        Subcategory subcategory = subcategoryRepo.findById(dto.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Подкатегория не найдена"));

        MenuItem menuItem = MenuItem.builder()
                .name(dto.getName())
                .imageUrl(dto.getImageUrl())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .isVegetarian(dto.isVegetarian())
                .restaurant(restaurant)
                .subcategory(subcategory)
                .build();

        menuItemRepo.save(menuItem);
        return mapToDto(menuItem);
    }

    @Override
    public List<MenuItemResponseDto> search(String query) {
        return menuItemRepo.search(query).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDto> filterByVegetarian(boolean isVegetarian) {
        return menuItemRepo.findByIsVegetarian(isVegetarian).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDto> sortByPrice(boolean ascending) {
        List<MenuItem> menuItems = ascending ?
                menuItemRepo.findAllByOrderByPriceAsc() :
                menuItemRepo.findAllByOrderByPriceDesc();
        return menuItems.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemResponseDto mapToDto(MenuItem menuItem) {
        MenuItemResponseDto dto = new MenuItemResponseDto();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setImageUrl(menuItem.getImageUrl());
        dto.setPrice(menuItem.getPrice());
        dto.setDescription(menuItem.getDescription());
        dto.setVegetarian(menuItem.isVegetarian());
        dto.setRestaurantName(menuItem.getRestaurant().getName());
        dto.setCategoryName(menuItem.getSubcategory().getCategory().getName());
        dto.setSubcategoryName(menuItem.getSubcategory().getName());
        return dto;
    }
}
