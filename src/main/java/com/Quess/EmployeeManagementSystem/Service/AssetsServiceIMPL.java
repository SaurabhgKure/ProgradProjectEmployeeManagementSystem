package com.Quess.EmployeeManagementSystem.Service;

import com.Quess.EmployeeManagementSystem.Models.Assets.Assets;
import com.Quess.EmployeeManagementSystem.Models.Employee.Employee;
import com.Quess.EmployeeManagementSystem.Repository.AssetsRepository;
import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetsServiceIMPL implements AssetsService {

    @Autowired
    private AssetsRepository assetsrepo;
    @Autowired
    private EmployeeRepository employeerepo;

    public AssetsServiceIMPL(AssetsRepository assetsrepo) {
        this.assetsrepo =assetsrepo;
    }

    @Override
    public Assets saveAsset(Assets assets) {
        return assetsrepo.save(assets);
    }

    @Override
    public List<Assets> getAllAssets() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee user = (Employee) authentication.getPrincipal();
        String urole =employeerepo.findByEmail(authentication.getName()).get().getRole();
        if(urole.equals("MANAGER") || urole.equals("EMPLOYEE")){
            List<Assets> emp= assetsrepo.findAll();
            return emp.stream().filter((i)->i.getOrganizationid()==user.getOrganizationid()).collect(Collectors.toList());
        }
        return assetsrepo.findAll();
    }

    @Override
    public Assets getAssetById(int id) {
        return assetsrepo.findById(id).orElseThrow(() -> new  com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Assets not found"));
    }

    @Override
    public Assets updateAsset(Assets assets, int id) {
        Assets existingDetail=assetsrepo.findById(id).orElseThrow(() -> new  com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Assets not found"));
        existingDetail.setAssetName(assets.getAssetName());
        existingDetail.setAssetType(assets.getAssetType());
        existingDetail.setAssetValue(assets.getAssetValue());
        existingDetail.setOrganizationid(assets.getOrganizationid());
        assetsrepo.save(existingDetail);
        return existingDetail;
    }

    @Override
    public void deleteAsset(int id) {
        assetsrepo.findById(id).orElseThrow(() -> new  com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Assets not found"));
        assetsrepo.deleteById(id);

    }
}
