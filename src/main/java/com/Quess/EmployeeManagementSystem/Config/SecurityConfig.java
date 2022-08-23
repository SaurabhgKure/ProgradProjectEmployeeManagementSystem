package com.Quess.EmployeeManagementSystem.Config;

import com.Quess.EmployeeManagementSystem.Security.EmployeeSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeSecurity employee;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/employee").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.PUT,"/employee/{id}").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.DELETE,"/employee/{id}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/employee").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.GET,"/employee/{id}").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.GET,"/employee/user/{id}").access("@userSecurity.hasUserId(authentication,#id)")

                .antMatchers(HttpMethod.DELETE,"/organization/{id}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/organization/{id}").hasAnyAuthority("ADMIN","MANAGER","EMPLOYEE")
                .antMatchers(HttpMethod.PUT,"/organization/{id}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/organization").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/organization").hasAnyAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/assets").hasAnyAuthority("ADMIN","MANAGER","EMPLOYEE")
                .antMatchers(HttpMethod.DELETE,"/assets/{id}").hasAnyAuthority("ADMIN","MANAGER","EMPLOYEE")
                .antMatchers(HttpMethod.GET,"/assets/{id}").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.POST,"/assets").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.PUT,"/assets/{id}").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.GET,"/assets").hasAnyAuthority("ADMIN","MANAGER","EMPLOYEE")




                .and().httpBasic();

        http.csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employee).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}