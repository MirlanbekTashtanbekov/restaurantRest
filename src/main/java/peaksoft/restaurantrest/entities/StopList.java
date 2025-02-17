package peaksoft.restaurantrest.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name ="stop_lists", uniqueConstraints = @UniqueConstraint(columnNames = {"menu_item_id", "date"}))
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopList {
    @Id
    @GeneratedValue(generator = "stopList_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "stopList_gen", sequenceName = "stopList_seq", allocationSize = 1)
    Long id;
    @Column(nullable = false)
    String reason;
    @Column(nullable = false)
    LocalDate date;

    @OneToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    MenuItem menuItem;
}
