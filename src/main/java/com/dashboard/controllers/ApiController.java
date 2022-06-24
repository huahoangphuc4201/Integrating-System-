package com.dashboard.controllers;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.servicesimp.UserDetailsServiceImp;

@RestController
public class ApiController {

	@Autowired
	private UserDetailsServiceImp userService;
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String, String> request) {	
		Map<String, String> response=userService.login(request.get("username"),request.get("password"));
		return response;
		
	}
	
}
