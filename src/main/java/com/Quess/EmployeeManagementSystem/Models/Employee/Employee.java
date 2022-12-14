package com.Quess.EmployeeManagementSystem.Models.Employee;



import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;


@Data
@Entity
@Table(name="Employee")
public class Employee implements UserDetails{
    public Employee(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    @NotEmpty(message = "Name Cannot be Empty...!!! ")
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String name;
    @Column(nullable = false)
    @Min(value = 18,message = "Minimum age Should be 18")
    @Max(value = 50,message = "Maximum age should be 50")
    private int age;
    @Column(nullable = false)
    @NotEmpty(message = "Contact number Cannot be Empty...!!! ")
    @Pattern(message="Contact number is not valid", regexp = "^[0-9]{10}$")
    private String contact;
    @Column(nullable = false)
    @NotEmpty(message = "Salary Cannot be Empty...!!! ")
    @Min(value = 10000,message = "salary Shoulde be minimum 10000")
    private String salary;
    @Column(nullable = false,unique = true)
    @NotEmpty(message = "Email Cannot be Empty...!!! ")
    @Email( message = "Email is not valid", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @Column(nullable = false)
   @NotEmpty(message = "Gender can not be Empty")
    private String gender;
    @Column(nullable = false)
    @NotEmpty(message = "Password Cannot be Empty...!!! ")
    @Pattern(message="password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit with atleast 8 characters",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    @Column(nullable = true)
    private int organizationid;
    @NotEmpty(message = "Role Can not be empty")
    @Pattern(message = "Enter Correct Role..!!!",regexp = "^EMPLOYEE$|^ADMIN$|^MANAGER$")
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
