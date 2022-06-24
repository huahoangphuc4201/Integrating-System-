package com.dashboard.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.DashboardEmployee;

@Repository
@Transactional
public interface DashboardEmployeeRepository extends JpaRepository<DashboardEmployee, Long> {

	@Query(value="select * from Employee as u where u.Employee_ID= :id",nativeQuery = true)
	DashboardEmployee findByEmployeeId(@Param("id")long id);
}
