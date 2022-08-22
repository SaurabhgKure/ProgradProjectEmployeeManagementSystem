package com.Quess.EmployeeManagementSystem.Config;

import com.Quess.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component("userSecurity")
public class UserSecurity {

    @Autowired
    EmployeeRepository userRepo;

    public boolean hasUserId(Authentication authentication, Integer userId) {

        int userID=userRepo.findByEmail(authentication.getName()).get().getId();

        if(userID==userId)
            return true;

        return false;

    }
}
