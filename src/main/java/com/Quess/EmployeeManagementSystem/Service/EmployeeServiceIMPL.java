package com.Quess.EmployeeManagementSystem.Service;



import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;
import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Quess.EmployeeManagementSystem.exception.EmailAreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceIMPL implements EmployeeService {
    @Autowired
    private EmployeeRepository employeerepo;
    PasswordEncoder passwordEncoder;

    private Employee employee;

    public EmployeeServiceIMPL(EmployeeRepository employeerepo) {
        this.passwordEncoder=new BCryptPasswordEncoder();
        this.employeerepo =employeerepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        if(employeerepo.findByEmail(employee.getEmail()).isPresent()) throw new EmailAreadyExists("Email already exists..!!");
        String encodepass=this.passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodepass);
        return employeerepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee user = (Employee) authentication.getPrincipal();
        String urole =employeerepo.findByEmail(authentication.getName()).get().getRole();
        if(urole.equals("MANAGER")){
            List<Employee> emp= employeerepo.findAll();
            return emp.stream().filter((i)->i.getOrganizationid()==user.getOrganizationid()).collect(Collectors.toList());
        }


            return employeerepo.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeerepo.findById(id).orElseThrow(() -> new  com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        Employee existingDetail=employeerepo.findById(id).orElseThrow(() -> new com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Employee not found"));
        String encodepass=this.passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodepass);
        existingDetail.setAge(employee.getAge());
        if(existingDetail.getEmail().equals(employee.getEmail()) || !(employeerepo.findByEmail(employee.getEmail()).isPresent())) {
            existingDetail.setEmail(employee.getEmail());
        }else {
            throw new EmailAreadyExists("Email already exists..!!");
        }
        existingDetail.setGender(employee.getGender());
        existingDetail.setContact(employee.getContact());
        existingDetail.setOrganizationid(employee.getOrganizationid());
        existingDetail.setName(employee.getName());
        existingDetail.setRole(employee.getRole());
        existingDetail.setSalary(employee.getSalary());
        employeerepo.save(existingDetail);
        return existingDetail;
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeerepo.findById(id).orElseThrow(() -> new com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Employee not found"));
        employeerepo.deleteById(id);

    }

}
