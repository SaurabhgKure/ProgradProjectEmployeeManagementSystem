package com.Quess.EmployeeManagementSystem.Service;

import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();
    Employee getEmployeeById(int id);
    Employee updateEmployee(Employee employee,int id);
    void deleteEmployeeById(int id);

    }
