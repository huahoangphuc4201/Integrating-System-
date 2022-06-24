package com.dashboard.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.Role;

@Repository
@Transactional
public interface DashboardRoleRepository extends JpaRepository<Role, Integer> {
	List<Role> findAll();
	Role findByRoleName(String roleName);
}
