package com.Quess.EmployeeManagementSystem.Repository;

import com.Quess.EmployeeManagementSystem.Models.Organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
    Optional<Organization> findById(int id);

//    @Query("SELECT name,address from organization where organization.id  ")
//    ResponseEntity<Organization> getOrganizationIDById(Integer id);
}
