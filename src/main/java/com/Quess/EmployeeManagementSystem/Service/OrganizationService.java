package com.Quess.EmployeeManagementSystem.Service;


import com.Quess.EmployeeManagementSystem.Models.Organization.Organization;

import java.util.List;

public interface OrganizationService {




    List<Organization> getAllOrganization();
    Organization getOrganizationById(int id);
    Organization updateOrganization(Organization organization,int id);
    public void deleteOrganizationid(int id);
    Organization saveOrganization(Organization organization);
}
