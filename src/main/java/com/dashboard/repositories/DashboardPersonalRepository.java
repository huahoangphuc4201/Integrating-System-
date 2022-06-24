package com.dashboard.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.DashboardPersonal;

@Repository
@Transactional
public interface DashboardPersonalRepository extends JpaRepository<DashboardPersonal, Long> {
	@Query(value="select * from Personal as u where u.Employee_ID = :id", nativeQuery = true)
	DashboardPersonal findByEmployeeId(@Param("id") long id);
	List<DashboardPersonal> findAll();

}
