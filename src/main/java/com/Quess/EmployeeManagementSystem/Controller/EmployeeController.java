package com.Quess.EmployeeManagementSystem.Controller;


import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;
import com.Quess.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployeeDetail(@RequestBody @Valid Employee employee)
    {

        return new ResponseEntity<Employee>(service.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Employee> getAllEmployee()
    {
        return service.getAllEmployee();
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")int id)
    {
            return new ResponseEntity<Employee>(service.getEmployeeById(id), HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Employee> getEmployeeByIde(@PathVariable("id")int id)
    {
        return new ResponseEntity<Employee>(service.getEmployeeById(id), HttpStatus.OK);

    }
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id")int id,@RequestBody @Valid Employee employee)
    {
        ;
        return new ResponseEntity<Employee>(service.updateEmployee(employee,id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id")int id)
    {
        service.deleteEmployeeById(id);
        return new ResponseEntity<String>("Employee deleted successfully",HttpStatus.OK);
    }
}
