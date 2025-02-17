package peaksoft.restaurantrest.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.restaurantrest.dto.ChequeRequestDto;
import peaksoft.restaurantrest.dto.ChequeResponseDto;
import peaksoft.restaurantrest.entities.Cheque;
import peaksoft.restaurantrest.entities.MenuItem;
import peaksoft.restaurantrest.entities.User;
import peaksoft.restaurantrest.enums.Role;
import peaksoft.restaurantrest.repo.ChequeRepo;
import peaksoft.restaurantrest.repo.MenuItemRepo;
import peaksoft.restaurantrest.repo.UserRepo;
import peaksoft.restaurantrest.service.ChequeService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {

    private final ChequeRepo chequeRepo;

    private final UserRepo userRepo;

    private final MenuItemRepo menuItemRepo;


    @Override
    public ChequeResponseDto createCheque(ChequeRequestDto dto, Role userRole) {
        if (userRole != Role.ADMIN && userRole != Role.WAITER) {
            throw new IllegalArgumentException("Только админ и официант могут создавать чеки!");
        }

        User waiter = userRepo.findById(dto.getWaiterId())
                .orElseThrow(() -> new RuntimeException("Официант не найден"));

        List<MenuItem> menuItems = menuItemRepo.findAllById(dto.getMenuItemIds());

        if (menuItems.isEmpty()) {
            throw new IllegalArgumentException("Меню не может быть пустым");
        }

        double averagePrice = menuItems.stream().mapToDouble(MenuItem::getPrice).average().orElse(0.0);
        double totalPrice = averagePrice + (averagePrice * dto.getServicePercent() / 100);

        Cheque cheque = Cheque.builder()
                .createdAt(LocalDate.now())
                .servicePercent(dto.getServicePercent())
                .totalPrice(totalPrice)
                .waiter(waiter)
                .menuItems(menuItems)
                .build();

        chequeRepo.save(cheque);
        return mapToDto(cheque);
    }

    @Override
    public double getTotalSalesForWaiter(Long waiterId, LocalDate date) {
        return chequeRepo.findByWaiterIdAndCreatedAt(waiterId, date).stream().mapToDouble(Cheque::getTotalPrice).sum();
    }

    @Override
    public double getAverageSalesForRestaurant(Long restaurantId, LocalDate date, Role userRole) {
        if (userRole != Role.ADMIN) {
            throw new IllegalArgumentException("Только админ может смотреть средн.сумму чеков ресторана");
        }
        List<Cheque> cheques = chequeRepo.findByWaiterRestaurantIdAndCreatedAt(restaurantId, date);
        return cheques.stream().mapToDouble(Cheque::getTotalPrice).average().orElse(0.0);
    }

    @Override
    public ChequeResponseDto mapToDto(Cheque cheque) {
        ChequeResponseDto dto = new ChequeResponseDto();
        dto.setId(cheque.getId());
        dto.setWaiterFullName(cheque.getWaiter().getFirstName()+" "+cheque.getWaiter().getLastName());
        dto.setMenuItemNames(cheque.getMenuItems().stream().map(MenuItem::getName).collect(Collectors.toList()));
        dto.setAveragePrice(cheque.getTotalPrice()/(1+(cheque.getServicePercent()/100)));
        dto.setServicePercent(cheque.getServicePercent());
        dto.setGrandTotal(cheque.getTotalPrice());
        dto.setCreatedAt(cheque.getCreatedAt());
        return dto;
    }


}
