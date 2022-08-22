package com.Quess.EmployeeManagementSystem.Repository;

import com.Quess.EmployeeManagementSystem.Models.Assets.Assets;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AssetsRepository extends JpaRepository<Assets,Integer> {
    Optional<Assets> findById(int id);
}
