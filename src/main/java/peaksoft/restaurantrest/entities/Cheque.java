package peaksoft.restaurantrest.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name ="cheques")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cheque {
    @Id
    @GeneratedValue(generator = "cheque_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cheque_gen", sequenceName = "cheque_seq", allocationSize = 1)
    Long id;
    double totalPrice;
    double servicePercent;
    LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "waiter_id", nullable = false)
    User waiter;

    @ManyToMany
//    @JoinTable(name = "cheque_menu_items",
//            joinColumns = @JoinColumn(name = "cheque_id"),
//            inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
    List<MenuItem> menuItems;


}
