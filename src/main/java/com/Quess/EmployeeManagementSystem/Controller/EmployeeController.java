package com.Quess.EmployeeManagementSystem.Controller;


import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;
import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Quess.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> saveEmployeeDetail(@RequestBody @Valid Employee employee) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userrole = employeeRepository.findByEmail(authentication.getName()).get().getRole();
        int userorg = employeeRepository.findByEmail(authentication.getName()).get().getOrganizationid();
        if (userrole.equals("MANAGER") && employee.getRole().equals("ADMIN")) {
            return new ResponseEntity<String>("Manager Can not Post ADMIN.", HttpStatus.UNAUTHORIZED);

        }
        if (userrole.equals("MANAGER") && employee.getRole().equals("MANAGER")) {
            return new ResponseEntity<String>("Manager Can not Post MANAGER.", HttpStatus.UNAUTHORIZED);

        }
        if (userrole.equals("MANAGER") && employee.getOrganizationid()!=userorg) {
            return new ResponseEntity<String>("Can not Post to Other Organization.", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<Employee>(service.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Employee> getAllEmployee() {
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String urole =employeeRepository.findByEmail(authentication.getName()).get().getRole();
        int uorg =employeeRepository.findByEmail(authentication.getName()).get().getOrganizationid();
        int userID = employeeRepository.findByEmail(authentication.getName()).get().getId();
        if(urole.equals("EMPLOYEE") && userID!=id){
            return new ResponseEntity<String>("Unauthorized Access.", HttpStatus.UNAUTHORIZED);

        }
        if(urole.equals("MANAGER") && service.getEmployeeById(id).getRole().equals("ADMIN")){
            return new ResponseEntity<String>("Unauthorized Access.", HttpStatus.UNAUTHORIZED);

        }
        if(urole.equals("MANAGER") && service.getEmployeeById(id).getOrganizationid()!=uorg){
            return new ResponseEntity<String>("Can not see other organization employee.", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<Employee>(service.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody @Valid Employee employee) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userrole = employeeRepository.findByEmail(authentication.getName()).get().getRole();
        int userorg = employeeRepository.findByEmail(authentication.getName()).get().getOrganizationid();
        int userID = employeeRepository.findByEmail(authentication.getName()).get().getId();
        if (userrole.equals("MANAGER") && service.getEmployeeById(id).getRole().equals("ADMIN")) {
            return new ResponseEntity<String>("Manager Can not Update ADMIN.", HttpStatus.UNAUTHORIZED);
        }
        if(userrole.equals("MANAGER") && service.getEmployeeById(id).getOrganizationid()!=userorg){
            return new ResponseEntity<String>("Can not Update Other organization employee.", HttpStatus.UNAUTHORIZED);

        }
        if (userrole.equals("MANAGER") && employee.getOrganizationid()!=userorg) {
            return new ResponseEntity<String>("Can not Update to Other organization employee.", HttpStatus.UNAUTHORIZED);
        }
        if (userrole.equals("MANAGER") && service.getEmployeeById(id).getRole().equals("MANAGER") && userID!=id) {
            return new ResponseEntity<String>("Manager Can not Update other MANAGER.", HttpStatus.UNAUTHORIZED);
        }
            return new ResponseEntity<Employee>(service.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id")int id)
    {
        service.deleteEmployeeById(id);
        return new ResponseEntity<String>("Employee deleted successfully",HttpStatus.OK);
    }
}
