package com.dashboard.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.DashboardBenefitPlans;

@Repository 
@Transactional
public interface DashboardBenefitPlansRepository extends JpaRepository<DashboardBenefitPlans, Long > {
	
	@Query(value="select * from Benefit_Plans as u where u.Benefit_Plan_ID=:id",nativeQuery = true)
	DashboardBenefitPlans findByBenefitPlansId(@Param("id")long id);
	
	List<DashboardBenefitPlans> findAll();
	
}
