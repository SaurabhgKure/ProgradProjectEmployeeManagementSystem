package com.Quess.EmployeeManagementSystem.Service;


import com.Quess.EmployeeManagementSystem.Models.Organization.Organization;
import com.Quess.EmployeeManagementSystem.Repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceIMPL implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationrepo;

    public OrganizationServiceIMPL(OrganizationRepository organizationrepo) {
        this.organizationrepo =organizationrepo;
    }



    @Override
    public List<Organization> getAllOrganization() {
        return organizationrepo.findAll();
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationrepo.findById(id).orElseThrow(() -> new  com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Organisation not found"));
    }

    @Override
    public Organization updateOrganization(Organization organization, int id) {
        Organization existingDetail=organizationrepo.findById(id).orElseThrow(() -> new  com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Organisation not found"));
        existingDetail.setName(organization.getName());
        existingDetail.setAddress(organization.getAddress());
        organizationrepo.save(existingDetail);
        return existingDetail;
    }

    @Override
    public void deleteOrganizationid(int id) {
        organizationrepo.findById(id).orElseThrow(() -> new  com.Quess.EmployeeManagementSystem.exception.ResourceNotFoundException("Organization not found"));
        organizationrepo.deleteById(id);


    }



    @Override
    public Organization saveOrganization(Organization organization) {
        return organizationrepo.save(organization);
    }
}
