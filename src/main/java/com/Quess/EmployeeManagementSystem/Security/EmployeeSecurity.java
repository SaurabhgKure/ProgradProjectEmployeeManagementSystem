package com.Quess.EmployeeManagementSystem.Security;


import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;
import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSecurity implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee detail= this.employeeRepo.findByEmail(username).orElseThrow();
        return detail;
    }

}
