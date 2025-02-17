package peaksoft.restaurantrest.api;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantrest.dto.StopListRequestDto;
import peaksoft.restaurantrest.dto.StopListResponseDto;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.service.StopListService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stop-list")
public class StopListApi {

    private final StopListService stopListService;

    @PostMapping
    public StopListResponseDto addToStopList(@RequestBody StopListRequestDto dto, @RequestParam Role userRole){
        return stopListService.addToStopList(dto, userRole);
    }

    @GetMapping
    public List<StopListResponseDto> getAll(){
        return stopListService.getAll();
    }

    @DeleteMapping("/{id}")
    public void removeFromStopList(@PathVariable Long id){
        stopListService.removeFromStopList(id);
    }

}
