package peaksoft.restaurantrest.service;

import peaksoft.restaurantrest.dto.ChequeRequestDto;
import peaksoft.restaurantrest.dto.ChequeResponseDto;
import peaksoft.restaurantrest.entities.Cheque;
import peaksoft.restaurantrest.enums.Role;

import java.time.LocalDate;

public interface ChequeService {

    public ChequeResponseDto createCheque(ChequeRequestDto dto, Role userRole);
    double getTotalSalesForWaiter(Long waiterId, LocalDate date);
    double getAverageSalesForRestaurant(Long restaurantId, LocalDate date, Role userRole);
    ChequeResponseDto mapToDto(Cheque cheque);



}
