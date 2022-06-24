package com.dashboard.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.DashboardPayrates;

@Repository
@Transactional
public interface DashboardPayratesRepository extends JpaRepository<DashboardPayrates, Integer>{

	@Query(value="select * from Pay_Rate as u where u.Pay_Rate_ID= :id",nativeQuery = true)
	DashboardPayrates findByPayrateId(@Param("id")int id);
	
}
