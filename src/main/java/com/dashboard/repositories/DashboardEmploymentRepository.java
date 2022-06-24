package com.dashboard.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.DashboardEmployment;

@Repository
@Transactional
public interface DashboardEmploymentRepository extends JpaRepository<DashboardEmployment, Long> {
	@Query(value="select * from Employment as u where u.Employee_ID= :id",nativeQuery = true)
	DashboardEmployment findByEmploymentId(@Param("id")long id);
}
