package com.dashboard.hrm.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.hrm.entities.HRMPersonal;

@Repository
@Transactional
public interface HRMPersonalRepository extends JpaRepository<HRMPersonal, Long> {
	List<HRMPersonal> findAll();
	HRMPersonal findByEmployeeId(Long id);
}
