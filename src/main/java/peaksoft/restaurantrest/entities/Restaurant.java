package peaksoft.restaurantrest.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name ="restaurants")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(generator = "restaurant_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "restaurant_gen", sequenceName = "restaurant_seq", allocationSize = 1)
    Long id;
    String name;
    String location;
    String restType;
    int numberOfEmployees =0;
    Integer service;

    @OneToMany(mappedBy = "restaurant")
    List<User> users;

    @OneToMany(mappedBy = "restaurant")
    List<MenuItem> menuItems;

}
