package peaksoft.restaurantrest.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantrest.dto.MenuItemRequestDto;
import peaksoft.restaurantrest.dto.MenuItemResponseDto;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.service.MenuItemService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu-items")
public class MenuItemApi {

    private final MenuItemService menuItemService;

    @PostMapping
    public MenuItemResponseDto createMenuItem(@RequestBody MenuItemRequestDto requestDto, @RequestParam Role userRole) {
        return menuItemService.createMenuItem(requestDto, userRole);
    }

    @GetMapping("/search")
    public List<MenuItemResponseDto> search(@RequestParam String query) {
        return menuItemService.search(query);
    }

    @GetMapping("/filter")
    public List<MenuItemResponseDto> filterByVegetarian(@RequestParam boolean isVegetarian) {
        return menuItemService.filterByVegetarian(isVegetarian);
    }

    @GetMapping("/sort")
    public List<MenuItemResponseDto> sortByPrice(@RequestParam boolean ascending) {
        return menuItemService.sortByPrice(ascending);
    }


}
