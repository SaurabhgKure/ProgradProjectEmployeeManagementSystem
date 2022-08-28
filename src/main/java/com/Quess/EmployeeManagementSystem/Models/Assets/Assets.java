package com.Quess.EmployeeManagementSystem.Models.Assets;


import lombok.Data;
import javax.persistence.*;
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
    @NotEmpty(message ="Name Can not be empty")
    private String assetName;
    @NotEmpty(message = "Type Can not be Empty")
    private String assetType;
    @NotEmpty(message = "Value Can not be Empty")
    private String assetValue;

    @Column(nullable = true)
    private int organizationid;



}
