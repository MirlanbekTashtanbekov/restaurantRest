package peaksoft.restaurantrest.service;

import peaksoft.restaurantrest.dto.StopListRequestDto;
import peaksoft.restaurantrest.dto.StopListResponseDto;
import peaksoft.restaurantrest.entities.StopList;
import peaksoft.restaurantrest.enums.Role;

import java.util.List;

public interface StopListService {

    StopListResponseDto addToStopList(StopListRequestDto dto, Role userRole);
    List<StopListResponseDto> getAll();
    void removeFromStopList(Long id);
    StopListResponseDto mapToDto(StopList stopList);

}
