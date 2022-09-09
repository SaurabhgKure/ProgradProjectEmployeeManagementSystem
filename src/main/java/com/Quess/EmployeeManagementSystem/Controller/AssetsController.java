package com.Quess.EmployeeManagementSystem.Controller;


import com.Quess.EmployeeManagementSystem.Models.Assets.Assets;
import com.Quess.EmployeeManagementSystem.Repository.AssetsRepository;
import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Quess.EmployeeManagementSystem.Service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assets/")
public class AssetsController {

    @Autowired
    private AssetsService service;
    @Autowired
    private AssetsRepository assetsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public AssetsController(AssetsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> saveAssetDetail(@RequestBody @Valid Assets assets)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userrole = employeeRepository.findByEmail(authentication.getName()).get().getRole();
        int userorg =employeeRepository.findByEmail(authentication.getName()).get().getOrganizationid();
        if(userrole.equals("MANAGER") && assets.getOrganizationid()!=userorg){
            return new ResponseEntity<String>("Can not add other organization asset.",HttpStatus.OK);

        }
        return new ResponseEntity<Assets>(service.saveAsset(assets), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Assets> getAllAssets()
    {
        return service.getAllAssets();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable("id")int id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userrole = employeeRepository.findByEmail(authentication.getName()).get().getRole();
        int userorg =employeeRepository.findByEmail(authentication.getName()).get().getOrganizationid();
        if((userrole.equals("MANAGER") && service.getAssetById(id).getOrganizationid()!=userorg) || (userrole.equals("EMPLOYEE") && service.getAssetById(id).getOrganizationid()!=userorg)){
            return new ResponseEntity<String>("Can not see other organization asset.",HttpStatus.OK);

        }
        return new ResponseEntity<Assets>(service.getAssetById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAsset(@PathVariable("id")int id,@RequestBody @Valid Assets assets)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userrole = employeeRepository.findByEmail(authentication.getName()).get().getRole();
        int userorg =employeeRepository.findByEmail(authentication.getName()).get().getOrganizationid();
        if(userrole.equals("MANAGER") && assets.getOrganizationid()!=userorg){
            return new ResponseEntity<String>("Can not update to other organization asset.",HttpStatus.OK);

        }
        if(userrole.equals("MANAGER") && service.getAssetById(id).getOrganizationid()!=userorg){
            return new ResponseEntity<String>("Can not update other organization asset.",HttpStatus.OK);

        }
        return new ResponseEntity<Assets>(service.updateAsset(assets,id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("id")int id)
    {
        service.deleteAsset(id);
        return new ResponseEntity<String>("Asset deleted successfully",HttpStatus.OK);
    }
}
