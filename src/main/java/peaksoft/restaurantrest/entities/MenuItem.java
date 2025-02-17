package peaksoft.restaurantrest.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name ="menu_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(generator = "menuItem_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "menuItem_gen", sequenceName = "menuItem_seq", allocationSize = 1)
    Long Id;
    @Column(nullable = false)
    String name;
    String imageUrl;
    @Column(nullable = false)
    Double price;
    String description;
    Boolean isVegetarian;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @ManyToMany(mappedBy = "menuItems")
    List<Cheque> cheques;

    @OneToOne
    StopList stopList;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    Subcategory subcategory;


    public boolean isVegetarian() {
        return false;
    }
}
