package peaksoft.restaurantrest.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.restaurantrest.dto.StopListRequestDto;
import peaksoft.restaurantrest.dto.StopListResponseDto;
import peaksoft.restaurantrest.entities.MenuItem;
import peaksoft.restaurantrest.entities.StopList;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.repo.MenuItemRepo;
import peaksoft.restaurantrest.repo.StopListRepo;
import peaksoft.restaurantrest.service.StopListService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {

    private final StopListRepo stopListRepo;

    private final MenuItemRepo menuItemRepo;


    @Override
    public StopListResponseDto addToStopList(StopListRequestDto dto, Role userRole) {
        if (userRole != Role.ADMIN && userRole != Role.CHEF) {
            throw new IllegalArgumentException("Только повар и админ могут добавлять блюда в стоп-лист.");
        }

        // Проверка, добавлялось ли уже это блюдо в стоп-лист сегодня
        if (stopListRepo.findByMenuItemIdAndDate(dto.getMenuItemId(), dto.getDate()).isPresent()) {
            throw new IllegalArgumentException("Это блюдо уже есть в стоп-листе на указанную дату.");
        }

        MenuItem menuItem = menuItemRepo.findById(dto.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Блюдо не найдено"));

        StopList stopList = StopList.builder()
                .menuItem(menuItem)
                .date(dto.getDate())
                .reason(dto.getReason())
                .build();

        stopListRepo.save(stopList);
        return mapToDto(stopList);
    }

    @Override
    public List<StopListResponseDto> getAll() {
        return stopListRepo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeFromStopList(Long id) {
        stopListRepo.deleteById(id);
    }

    @Override
    public StopListResponseDto mapToDto(StopList stopList) {
        StopListResponseDto dto = new StopListResponseDto();
        dto.setId(stopList.getId());
        dto.setMenuItemName(stopList.getMenuItem().getName());
        dto.setDate(stopList.getDate());
        dto.setReason(stopList.getReason());
        return dto;
    }
}
