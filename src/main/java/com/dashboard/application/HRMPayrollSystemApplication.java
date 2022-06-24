package com.dashboard.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.dashboard.controllers.UserController;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class })
@ComponentScan(basePackages = {"com.dashboard.*"},basePackageClasses = {UserController.class})
public class HRMPayrollSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HRMPayrollSystemApplication.class, args);
	}
	
}
