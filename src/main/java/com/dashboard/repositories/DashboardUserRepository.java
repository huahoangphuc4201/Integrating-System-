package com.dashboard.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.User;

@Repository
@Transactional
public interface DashboardUserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	@Query(value="select * from Account as u where u.Employee_ID = :id", nativeQuery = true)
	User findById(@Param("id") long id);
	
}
