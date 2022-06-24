package com.dashboard.controllers;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dashboard.entities.DashboardPersonal;
import com.dashboard.entities.MyUserDetails;
import com.dashboard.services.DashboardPersonalService;
import com.dashboard.servicesimp.UserDetailsServiceImp;

@Controller
public class UserController {
	
	private List<String> alerts= new ArrayList<String>();
	
	@Autowired
	private DashboardPersonalService dashboardPersonalService;
	
	@Autowired
	private UserDetailsServiceImp userService;
	
	@GetMapping({"/login","/"})
	public String login() {
        return "index";
	}
	
	@PostMapping("/update-profile")
	public String updateProfile(@RequestParam Map<String,String> request,Model model,Principal principal) {
		String message="";
		long id= Long.parseLong(request.get("employeeID"));
		String address=request.get("address1");
		String city=request.get("city");
		String email=request.get("email");
		String phone=request.get("phoneNumber");
		long zip=Long.parseLong(request.get("zip"));
		String ssn=request.get("ssn");
		if(dashboardPersonalService.updateProfile(id, address, city, email, phone, zip, ssn))
			message="success";
		else
			message="fail";
		model.addAttribute("message",message);
		return homepage(model, principal);
	}
	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam Map<String,String> request,Model model,Principal principal) {
		userService.changePassword(principal.getName(), request.get("newPassword"));
		return homepage(model, principal);
	}
	
	@PostMapping("/create-account")
	public String signIn(@RequestParam Map<String,String> user,Model model,Principal principal){

		if(userService.create(Long.parseLong(user.get("employeeID")),user.get("username"), user.get("password"), user.get("role"))) 
			model.addAttribute("message", "success");
		else
			model.addAttribute("message", "fail");
		List<Map<String,String>>accounts=dashboardPersonalService.getAccount();
		MyUserDetails myuser=(MyUserDetails) userService.loadUserByUsername(principal.getName());
		DashboardPersonal personal= dashboardPersonalService.findByEmployeeId(myuser.getID());
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
	
	@GetMapping("/remove-account")
	public String deleteAccount(@RequestParam Map<String,String> request,Model model,Principal principal) {
		String message="";
		if(userService.deleteAccount(Long.parseLong(request.get("id"))))
			message="removed";
		model.addAttribute("delelteMessage", message);
		List<Map<String,String>>accounts=dashboardPersonalService.getAccount();
		MyUserDetails myuser=(MyUserDetails) userService.loadUserByUsername(principal.getName());
		DashboardPersonal personal= dashboardPersonalService.findByEmployeeId(myuser.getID());
		model.addAttribute("myname",personal.getFirstName()+" "+personal.getLastName());
		model.addAttribute("accounts",accounts);
		model.addAttribute("message", message);
		List<Map<String,String>> alert= dashboardPersonalService.getEmployeeOverDayoff();
		for (String mess :alerts) {
			Map<String,String> object= new HashMap<String, String>();
			object.put("message",mess);
			alert.add(object);
		}
		model.addAttribute("alerts",alert);
		return "account_setting";
	}
	
	@GetMapping("/homepage")
	public String homepage(Model model,Principal principal) {
		MyUserDetails myuser=(MyUserDetails) userService.loadUserByUsername(principal.getName());
		DashboardPersonal personal= dashboardPersonalService.findByEmployeeId(myuser.getID());
		model.addAttribute("myname",personal.getFirstName()+" "+personal.getLastName());
		for (GrantedAuthority item : userService.loadUserByUsername(principal.getName()).getAuthorities()) {
			if(item.getAuthority().equals("CEO")) {
				List<DashboardPersonal> personals = dashboardPersonalService.findAll();
				//----shareholder----
				long shareholder_dpm11=0,shareholder_dpm21=0,shareholder_dpm31=0,shareholder_dpm41=0,shareholder_dpm51=0,shareholder_dpm61=0;
				long shareholder_dpm12=0,shareholder_dpm22=0,shareholder_dpm32=0,shareholder_dpm42=0,shareholder_dpm52=0,shareholder_dpm62=0;
				
				long shareholder_bp11=0,shareholder_bp21=0,shareholder_bp31=0,shareholder_bp41=0,shareholder_bp51=0;
				long shareholder_bp12=0,shareholder_bp22=0,shareholder_bp32=0,shareholder_bp42=0,shareholder_bp52=0;
				
				int shareholder_vacation=0;
				//----male----
				long male_dpm11=0,male_dpm21=0,male_dpm31=0,male_dpm41=0,male_dpm51=0,male_dpm61=0;
				long male_dpm12=0,male_dpm22=0,male_dpm32=0,male_dpm42=0,male_dpm52=0,male_dpm62=0;
				
				int male_vacation=0;
				//----female----
				long female_dpm11=0,female_dpm21=0,female_dpm31=0,female_dpm41=0,female_dpm51=0,female_dpm61=0;
				long female_dpm12=0,female_dpm22=0,female_dpm32=0,female_dpm42=0,female_dpm52=0,female_dpm62=0;
				
				int female_vacation=0;
				//----full----
				long full_dpm11=0,full_dpm21=0,full_dpm31=0,full_dpm41=0,full_dpm51=0,full_dpm61=0;
				long full_dpm12=0,full_dpm22=0,full_dpm32=0,full_dpm42=0,full_dpm52=0,full_dpm62=0;
				
				int full_vacation=0;
				//----part----
				long part_dpm11=0,part_dpm21=0,part_dpm31=0,part_dpm41=0,part_dpm51=0,part_dpm61=0;
				long part_dpm12=0,part_dpm22=0,part_dpm32=0,part_dpm42=0,part_dpm52=0,part_dpm62=0;
				
				int part_vacation=0;
				for (DashboardPersonal dashboardPersonal : personals) {
					//----shareholder----
					if(dashboardPersonal.getShareholderStatus()) {
						String dpm= dashboardPersonal.getJobHistories().get(0).getDepartment();
						shareholder_vacation+=dashboardPersonal.getEmployee().getVacationDays();
						
						long bp=dashboardPersonal.getBenefitPlans().getBenefitPlanId();
						if(dpm.equals("department1")) {
							shareholder_dpm11+=dashboardPersonal.getEmployee().getPaidToDate();
							shareholder_dpm12+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department2")){
							shareholder_dpm21+=dashboardPersonal.getEmployee().getPaidToDate();
							shareholder_dpm22+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department3")){
							shareholder_dpm31+=dashboardPersonal.getEmployee().getPaidToDate();
							shareholder_dpm32+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department4")){
							shareholder_dpm41+=dashboardPersonal.getEmployee().getPaidToDate();
							shareholder_dpm42+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department5")){
							shareholder_dpm51+=dashboardPersonal.getEmployee().getPaidToDate();
							shareholder_dpm52+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else{
							shareholder_dpm61+=dashboardPersonal.getEmployee().getPaidToDate();
							shareholder_dpm62+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						if(bp==1) {
							shareholder_bp11+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else if(bp==2) {
							shareholder_bp21+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else if(bp==3) {
							shareholder_bp31+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else if(bp==4) {
							shareholder_bp41+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else {
							shareholder_bp51+=dashboardPersonal.getEmployee().getPaidToDate();
						}
					}
					//----non-shareholder----
					if(!dashboardPersonal.getShareholderStatus()) {
						long bp=dashboardPersonal.getBenefitPlans().getBenefitPlanId();
						if(bp==1) {
							shareholder_bp12+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else if(bp==2) {
							shareholder_bp22+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else if(bp==3) {
							shareholder_bp32+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else if(bp==4) {
							shareholder_bp42+=dashboardPersonal.getEmployee().getPaidToDate();
						}
						else {
							shareholder_bp52+=dashboardPersonal.getEmployee().getPaidToDate();
						}
					}
					//----male----
					if(dashboardPersonal.getGender()) {
						male_vacation+=dashboardPersonal.getEmployee().getVacationDays();
						
						String dpm= dashboardPersonal.getJobHistories().get(0).getDepartment();
						if(dpm.equals("department1")) {
							male_dpm11+=dashboardPersonal.getEmployee().getPaidToDate();
							male_dpm12+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department2")){
							male_dpm21+=dashboardPersonal.getEmployee().getPaidToDate();
							male_dpm22+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department3")){
							male_dpm31+=dashboardPersonal.getEmployee().getPaidToDate();
							male_dpm32+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department4")){
							male_dpm41+=dashboardPersonal.getEmployee().getPaidToDate();
							male_dpm42+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department5")){
							male_dpm51+=dashboardPersonal.getEmployee().getPaidToDate();
							male_dpm52+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else{
							male_dpm61+=dashboardPersonal.getEmployee().getPaidToDate();
							male_dpm62+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
					}
					//----female----
					if(!dashboardPersonal.getGender()) {
						female_vacation+=dashboardPersonal.getEmployee().getVacationDays();
						String dpm= dashboardPersonal.getJobHistories().get(0).getDepartment();
						if(dpm.equals("department1")) {
							female_dpm11+=dashboardPersonal.getEmployee().getPaidToDate();
							female_dpm12+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department2")){
							female_dpm21+=dashboardPersonal.getEmployee().getPaidToDate();
							female_dpm22+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department3")){
							female_dpm31+=dashboardPersonal.getEmployee().getPaidToDate();
							female_dpm32+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department4")){
							female_dpm41+=dashboardPersonal.getEmployee().getPaidToDate();
							female_dpm42+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department5")){
							female_dpm51+=dashboardPersonal.getEmployee().getPaidToDate();
							female_dpm52+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else{
							female_dpm61+=dashboardPersonal.getEmployee().getPaidToDate();
							female_dpm62+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
					}
					//----full----
					if(dashboardPersonal.getEmployment().getEmploymentStatus().equals("Full-time")) {
						full_vacation+=dashboardPersonal.getEmployee().getVacationDays();
						String dpm= dashboardPersonal.getJobHistories().get(0).getDepartment();
						if(dpm.equals("department1")) {
							full_dpm11+=dashboardPersonal.getEmployee().getPaidToDate();
							full_dpm12+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department2")){
							full_dpm21+=dashboardPersonal.getEmployee().getPaidToDate();
							full_dpm22+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department3")){
							full_dpm31+=dashboardPersonal.getEmployee().getPaidToDate();
							full_dpm32+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department4")){
							full_dpm41+=dashboardPersonal.getEmployee().getPaidToDate();
							full_dpm42+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department5")){
							full_dpm51+=dashboardPersonal.getEmployee().getPaidToDate();
							full_dpm52+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else{
							full_dpm61+=dashboardPersonal.getEmployee().getPaidToDate();
							full_dpm62+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
					}
					//----part----
					if(dashboardPersonal.getEmployment().getEmploymentStatus().equals("Part-time")) {
						part_vacation+=dashboardPersonal.getEmployee().getVacationDays();
						String dpm= dashboardPersonal.getJobHistories().get(0).getDepartment();
						if(dpm.equals("department1")) {
							part_dpm11+=dashboardPersonal.getEmployee().getPaidToDate();
							part_dpm12+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department2")){
							part_dpm21+=dashboardPersonal.getEmployee().getPaidToDate();
							part_dpm22+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department3")){
							part_dpm31+=dashboardPersonal.getEmployee().getPaidToDate();
							part_dpm32+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department4")){
							part_dpm41+=dashboardPersonal.getEmployee().getPaidToDate();
							part_dpm42+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else if(dpm.equals("department5")){
							part_dpm51+=dashboardPersonal.getEmployee().getPaidToDate();
							part_dpm52+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
						else{
							part_dpm61+=dashboardPersonal.getEmployee().getPaidToDate();
							part_dpm62+=dashboardPersonal.getEmployee().getPaidLastYear();
						}
					}
					
				}
				//----shareholder----
				List<Long> paidToDateShareholder= new ArrayList<Long>();
				List<Long> paidLastYearShareholder= new ArrayList<Long>();
				
				paidToDateShareholder.add(shareholder_dpm11);
				paidToDateShareholder.add(shareholder_dpm21);
				paidToDateShareholder.add(shareholder_dpm31);
				paidToDateShareholder.add(shareholder_dpm41);
				paidToDateShareholder.add(shareholder_dpm51);
				paidToDateShareholder.add(shareholder_dpm61);

				paidLastYearShareholder.add(shareholder_dpm12);
				paidLastYearShareholder.add(shareholder_dpm22);
				paidLastYearShareholder.add(shareholder_dpm32);
				paidLastYearShareholder.add(shareholder_dpm42);
				paidLastYearShareholder.add(shareholder_dpm52);
				paidLastYearShareholder.add(shareholder_dpm62);
				
				model.addAttribute("paidToDateShareholder",paidToDateShareholder);
				model.addAttribute("paidLastYearShareholder",paidLastYearShareholder);
				//----male----
				List<Long> paidToDateMale= new ArrayList<Long>();
				List<Long> paidLastYearMale= new ArrayList<Long>();
				
				paidToDateMale.add(male_dpm11);
				paidToDateMale.add(male_dpm21);
				paidToDateMale.add(male_dpm31);
				paidToDateMale.add(male_dpm41);
				paidToDateMale.add(male_dpm51);
				paidToDateMale.add(male_dpm61);

				paidLastYearMale.add(male_dpm12);
				paidLastYearMale.add(male_dpm22);
				paidLastYearMale.add(male_dpm32);
				paidLastYearMale.add(male_dpm42);
				paidLastYearMale.add(male_dpm52);
				paidLastYearMale.add(male_dpm62);
				
				model.addAttribute("paidToDateMale",paidToDateMale);
				model.addAttribute("paidLastYearMale",paidLastYearMale);
				//----female----
				List<Long> paidToDateFemale= new ArrayList<Long>();
				List<Long> paidLastYearFemale= new ArrayList<Long>();
				
				paidToDateFemale.add(female_dpm11);
				paidToDateFemale.add(female_dpm21);
				paidToDateFemale.add(female_dpm31);
				paidToDateFemale.add(female_dpm41);
				paidToDateFemale.add(female_dpm51);
				paidToDateFemale.add(female_dpm61);

				paidLastYearFemale.add(female_dpm12);
				paidLastYearFemale.add(female_dpm22);
				paidLastYearFemale.add(female_dpm32);
				paidLastYearFemale.add(female_dpm42);
				paidLastYearFemale.add(female_dpm52);
				paidLastYearFemale.add(female_dpm62);
				
				model.addAttribute("paidToDateFemale",paidToDateFemale);
				model.addAttribute("paidLastYearFemale",paidLastYearFemale);
				//----full----
				List<Long> paidToDateFull= new ArrayList<Long>();
				List<Long> paidLastYearFull= new ArrayList<Long>();
				
				paidToDateFull.add(full_dpm11);
				paidToDateFull.add(full_dpm21);
				paidToDateFull.add(full_dpm31);
				paidToDateFull.add(full_dpm41);
				paidToDateFull.add(full_dpm51);
				paidToDateFull.add(full_dpm61);

				paidLastYearFull.add(full_dpm12);
				paidLastYearFull.add(full_dpm22);
				paidLastYearFull.add(full_dpm32);
				paidLastYearFull.add(full_dpm42);
				paidLastYearFull.add(full_dpm52);
				paidLastYearFull.add(full_dpm62);
				
				model.addAttribute("paidToDateFull",paidToDateFull);
				model.addAttribute("paidLastYearFull",paidLastYearFull);
				//----part----
				List<Long> paidToDatePart= new ArrayList<Long>();
				List<Long> paidLastYearPart= new ArrayList<Long>();
				
				paidToDatePart.add(part_dpm11);
				paidToDatePart.add(part_dpm21);
				paidToDatePart.add(part_dpm31);
				paidToDatePart.add(part_dpm41);
				paidToDatePart.add(part_dpm51);
				paidToDatePart.add(part_dpm61);

				paidLastYearPart.add(part_dpm12);
				paidLastYearPart.add(part_dpm22);
				paidLastYearPart.add(part_dpm32);
				paidLastYearPart.add(part_dpm42);
				paidLastYearPart.add(part_dpm52);
				paidLastYearPart.add(part_dpm62);

				model.addAttribute("paidToDatePart",paidToDatePart);
				model.addAttribute("paidLastYearPart",paidLastYearPart);
				//----benefit----
				List<Long> shareholder =new ArrayList<Long>();
				List<Long> nonshareholder= new ArrayList<Long>();
				
				shareholder.add(shareholder_bp11);
				shareholder.add(shareholder_bp21);
				shareholder.add(shareholder_bp31);
				shareholder.add(shareholder_bp41);
				shareholder.add(shareholder_bp51);

				nonshareholder.add(shareholder_bp12);
				nonshareholder.add(shareholder_bp22);
				nonshareholder.add(shareholder_bp32);
				nonshareholder.add(shareholder_bp42);
				nonshareholder.add(shareholder_bp52);
				
				//----total vacation----
				List<Integer> vacationDays= new ArrayList<Integer>();
				vacationDays.add(shareholder_vacation);
				vacationDays.add(male_vacation);
				vacationDays.add(female_vacation);
				vacationDays.add(full_vacation);
				vacationDays.add(part_vacation);
				
				model.addAttribute("vacationDays",vacationDays);

				model.addAttribute("shareholder",shareholder);
				model.addAttribute("nonshareholder",nonshareholder);
				
				model.addAttribute("anniversaries",dashboardPersonalService.getAnninersaryInMonth());
				
				List<Map<String,String>> alert= dashboardPersonalService.getEmployeeOverDayoff();
				for (String mess :alerts) {
					Map<String,String> object= new HashMap<String, String>();
					object.put("message",mess);
					alert.add(object);
				}
				model.addAttribute("alerts",alert);
				
				return "admin_homepage";
			}
		}
		return "redirect:/profile";
	}
	

	
	@GetMapping("/profile")
	public String profile(Model model,Principal principal) {
		MyUserDetails myuser=(MyUserDetails) userService.loadUserByUsername(principal.getName());
		DashboardPersonal personal= dashboardPersonalService.findByEmployeeId(myuser.getID());
		model.addAttribute("myname",personal.getFirstName()+" "+personal.getLastName());
		String anniversary="No event";
		long totalAmount=personal.getEmployee().getPaidToDate();
		int vacationDay=personal.getEmployee().getVacationDays();
		Date date1=new Date(personal.getEmployment().getHireDate().getTime());
		Date date2=new Date(System.currentTimeMillis());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		if(calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH)) {
			int remainday = convertDate(calendar2.get(Calendar.DAY_OF_MONTH),calendar2.get(Calendar.MONTH)+1,calendar2.get(Calendar.YEAR))
							-convertDate(calendar1.get(Calendar.DAY_OF_MONTH),calendar1.get(Calendar.MONTH)+1,calendar1.get(Calendar.YEAR));
			if(remainday<=10) {
				int year=calendar2.get(Calendar.YEAR)-calendar1.get(Calendar.YEAR);
				if(remainday>0)
					anniversary=remainday+" day(s) left to "+year +" year(s) hiring anniversary";
				if(remainday==0)
					anniversary="Happy "+year +" year(s) hiring anniversary";
			}
		}
		String role="";
		for (GrantedAuthority item : myuser.getAuthorities()) {
			if(item.getAuthority().equals("CEO")) {
				role="CEO";
				model.addAttribute("alertDayoff",dashboardPersonalService.getEmployeeOverDayoff());
				List<Map<String,String>> alert= dashboardPersonalService.getEmployeeOverDayoff();
				for (String mess :alerts) {
					Map<String,String> object= new HashMap<String, String>();
					object.put("message",mess);
					alert.add(object);
				}
				model.addAttribute("alerts",alert);
			}
			else
				role="USER";
		}
		model.addAttribute("role", role);
		model.addAttribute("anniversary",anniversary);
		model.addAttribute("totalAmount",totalAmount);
		model.addAttribute("vacationDay",vacationDay);
		model.addAttribute("employeeID",personal.getEmployeeId());
		model.addAttribute("firstname",personal.getFirstName());
		model.addAttribute("lastname",personal.getLastName());
		model.addAttribute("address1",personal.getAddress1());
		model.addAttribute("city",personal.getCity());
		model.addAttribute("email",personal.getEmail());
		model.addAttribute("phoneNumber",personal.getPhoneNumber());
		model.addAttribute("zip",personal.getZip());
		model.addAttribute("ssn",personal.getSocialSecurityNumber());
		return "user_homepage";
	}
	
	@GetMapping("/synchronize")
	public String synchronize(Model model,Principal principal) {
		dashboardPersonalService.synchronize();
		return homepage(model,principal);
	}
	
	private int convertDate(int day, int month, int year) {
		int count =0;
		switch(month) {
			case 12: count+=30;
			case 11: count+=31;
			case 10: count+=30;
			case 9: count+=31;
			case 8: count+=31;
			case 7: count+=30;
			case 6: count+=31;
			case 5: count+=30;
			case 4: count+=31;
			case 3: if((year%4==0&&year%100!=0)||year%400==0) count+=29;
					else count+=28;
			case 2: count+=31;
			case 1: count+=day;
		}
		return count;
	}
	
}
