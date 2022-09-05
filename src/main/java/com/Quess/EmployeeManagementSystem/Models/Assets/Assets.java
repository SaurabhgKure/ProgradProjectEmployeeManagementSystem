package com.Quess.EmployeeManagementSystem.Models.Assets;


import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Assets")
public class Assets {


    public Assets() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message ="Name Cannot be empty")
    private String assetName;
    @NotEmpty(message = "Type Cannot be Empty")
    private String assetType;
    @Min(value = 1,message = "Minimum value should be 1.")
    private String assetValue;

    @Column(nullable = true)
    private int organizationid;



}
