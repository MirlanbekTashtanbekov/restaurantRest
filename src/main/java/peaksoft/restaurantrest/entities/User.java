package peaksoft.restaurantrest.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import peaksoft.restaurantrest.enums.RequestStatus;
import peaksoft.restaurantrest.enums.Role;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    Long id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    LocalDate dateOfBirth;
    @Column(unique = true, nullable = false)
    String email;
    @Column(unique = true, nullable = false)
    String password;
    @Column(nullable = false)
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Role role;
    int experience;

    @Enumerated(EnumType.STRING)
    RequestStatus status = RequestStatus.PENDING;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    Restaurant restaurant;

    @OneToMany(mappedBy = "waiter")
    List<Cheque> chequeList;


}
