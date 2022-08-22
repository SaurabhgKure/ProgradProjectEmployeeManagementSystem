package com.Quess.EmployeeManagementSystem.Models.Assets;


import com.Quess.EmployeeManagementSystem.Models.Organization.Organization;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "Assets")
public class Assets {


    public Assets() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message ="Name Can not be empty")
    private String assetName;
    @NotEmpty(message = "Type Can not be Empty")
    private String assetType;
    @NotEmpty(message = "Value Can not be Empty")
    private String assetValue;

    private int organizationid;



}
