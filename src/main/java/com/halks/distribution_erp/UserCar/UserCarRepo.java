package com.halks.distribution_erp.UserCar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCarRepo extends JpaRepository<UserCar, Long> {

    List<UserCar> findByOwner_Id(Long userId);

}
