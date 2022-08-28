package com.Quess.EmployeeManagementSystem.Models.Organization;


import com.Quess.EmployeeManagementSystem.Models.Assets.Assets;
import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "Organization")
public class Organization  {

    public Organization() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String name;
    @NotEmpty(message = "adress can not be empty")
    private String address;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "organizationid",referencedColumnName = "id")
    private Set<Assets> assets = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "organizationid",referencedColumnName = "id")
    private Set<Employee> employees = new HashSet<>();




}
