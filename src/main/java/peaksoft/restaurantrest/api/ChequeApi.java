package peaksoft.restaurantrest.api;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantrest.dto.ChequeRequestDto;
import peaksoft.restaurantrest.dto.ChequeResponseDto;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.service.ChequeService;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cheques")
public class ChequeApi {

    private final ChequeService chequeService;

    @PostMapping
    public ChequeResponseDto createCheque(@RequestBody ChequeRequestDto dto, @RequestParam Role userRole){
        return chequeService.createCheque(dto, userRole);
    }

    @GetMapping("/waiter/{waiterId}")
    public double getTotalSalesForWaiter(@PathVariable Long waiterId, @RequestParam LocalDate date){
        return chequeService.getTotalSalesForWaiter(waiterId, date);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public double getAverageSalesForRestaurant(@PathVariable Long restaurantId, @RequestParam LocalDate date, @RequestParam Role userRole){
        return chequeService.getAverageSalesForRestaurant(restaurantId, date, userRole);
    }


}
