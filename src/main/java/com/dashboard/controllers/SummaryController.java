package com.dashboard.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dashboard.entities.DashboardPersonal;
import com.dashboard.entities.MyUserDetails;
import com.dashboard.services.DashboardPersonalService;
import com.dashboard.servicesimp.UserDetailsServiceImp;

@Controller
public class SummaryController {

	private List<String> alerts= new ArrayList<String>();
	
	@Autowired
	private DashboardPersonalService dashboardPersonalService;
	
	@Autowired
	private UserDetailsServiceImp userService;
	
	@GetMapping("/tables")
	public String getTablePage(Model model, Principal principal) {
		List<Map<String,String>>employees= dashboardPersonalService.findAllEmployeeInfo();
		MyUserDetails user=(MyUserDetails) userService.loadUserByUsername(principal.getName());
		DashboardPersonal personal= dashboardPersonalService.findByEmployeeId(user.getID());		
		model.addAttribute("myname",personal.getFirstName()+" "+personal.getLastName());
		model.addAttribute("employees",employees);
		List<Map<String,String>> alert= dashboardPersonalService.getEmployeeOverDayoff();
		for (String mess :alerts) {
			Map<String,String> object= new HashMap<String, String>();
			object.put("message",mess);
			alert.add(object);
		}
		model.addAttribute("alerts",alert);
		return "tables";
	}
	
	@GetMapping("/account-setting")
	public String getAccountPage(Model model,Principal principal) {
		List<Map<String,String>>accounts=dashboardPersonalService.getAccount();
		MyUserDetails user=(MyUserDetails) userService.loadUserByUsername(principal.getName());
		DashboardPersonal personal= dashboardPersonalService.findByEmployeeId(user.getID());
		model.addAttribute("myname",personal.getFirstName()+" "+personal.getLastName());
		model.addAttribute("accounts",accounts);
		List<Map<String,String>> alert= dashboardPersonalService.getEmployeeOverDayoff();
		for (String mess :alerts) {
			Map<String,String> object= new HashMap<String, String>();
			object.put("message",mess);
			alert.add(object);
		}
		model.addAttribute("alerts",alert);
		return "account_setting";
	}
	
}
