package com.dashboard.servicesimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.entities.Role;
import com.dashboard.repositories.DashboardRoleRepository;
import com.dashboard.services.DashboardRoleService;

@Service
public class DashboardRoleServiceImp implements DashboardRoleService {

	@Autowired
	private DashboardRoleRepository dashboardRoleRepo;
	
	@Override
	public List<Role> findAll() {
		return dashboardRoleRepo.findAll();
	}

}
