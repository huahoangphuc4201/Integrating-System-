package com.dashboard.hrm.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dashboard.hrm.entities.HRMBenefitPlans;

@Repository
@Transactional
public interface HRMBenefitPlansRepository extends JpaRepository<HRMBenefitPlans, Long> {
	

}
