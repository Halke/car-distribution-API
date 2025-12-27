package com.halks.distribution_erp.User;

import com.halks.distribution_erp.UserCar.UserCar;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Nonnull
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Nonnull
    private String firstName;

    @Nonnull
    private String lastName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCar> ownedCars = new ArrayList<>();

}
