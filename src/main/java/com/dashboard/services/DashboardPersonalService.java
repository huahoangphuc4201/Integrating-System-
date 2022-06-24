package com.dashboard.services;

import java.util.List;
import java.util.Map;

import com.dashboard.entities.DashboardPersonal;

public interface DashboardPersonalService {
	DashboardPersonal findByEmployeeId(long id);
	List<Map<String,String>> findAllEmployeeInfo();
	List<DashboardPersonal> findAll();
	List<Map<String,String>> getAccount();
	Boolean updateProfile(long id,String address, String city, String email,String phone,long zip,String ssn);
	List<Map<String,String>> getEmployeeOverDayoff();
	List<Map<String,String>> getAnninersaryInMonth();
	void synchronize();
}
