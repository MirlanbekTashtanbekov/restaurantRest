package peaksoft.restaurantrest.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name ="subcategories")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subcategory {
    @Id
    @GeneratedValue(generator = "subcategory_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subcategory_gen", sequenceName = "subcategory_seq", allocationSize = 1)
    Long id;
    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "subcategory")
    List<MenuItem> menuItemList;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
