package com.Quess.EmployeeManagementSystem.Service;



import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;
import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String encodepass=this.passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodepass);
        return employeerepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
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
        existingDetail.setEmail(employee.getEmail());
        existingDetail.setGender(employee.getGender());
        existingDetail.setContact(employee.getContact());
        existingDetail.setName(employee.getName());
        employeerepo.save(existingDetail);
        return existingDetail;
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeerepo.findById(id).orElseThrow(() -> new com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Employee not found"));
        employeerepo.deleteById(id);

    }

}
