package com.Quess.EmployeeManagementSystem.Controller;
import com.Quess.EmployeeManagementSystem.Models.Organization.Organization;
import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Quess.EmployeeManagementSystem.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organization/")
public class OrganizationController {

    @Autowired
    private OrganizationService service;
    @Autowired
    private EmployeeRepository employeeRepository;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@RequestBody @Valid Organization organization)
    {
        return new ResponseEntity<Organization>(service.saveOrganization(organization), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Organization> getAllOrganization()

    {
        return service.getAllOrganization();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable("id")int id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String urole =employeeRepository.findByEmail(authentication.getName()).get().getRole();
        int uorg =employeeRepository.findByEmail(authentication.getName()).get().getOrganizationid();
        if((urole.equals("MANAGER")&&uorg!=id) || (urole.equals("EMPLOYEE")&&uorg!=id)){
            return new ResponseEntity<String>("Can not see other organization",HttpStatus.OK);

        }
        return new ResponseEntity<Organization>(service.getOrganizationById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable("id")int id,@RequestBody @Valid Organization organization)
    {
        ;
        return new ResponseEntity<Organization>(service.updateOrganization(organization,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganizationById(@PathVariable("id")int id)
    {
        service.deleteOrganizationid(id);
        return new ResponseEntity<String>("Organization Detail Deleted Successfully",HttpStatus.OK);
    }
}

