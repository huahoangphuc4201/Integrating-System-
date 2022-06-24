package com.dashboard.payroll.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.payroll.entities.PayrollEmployee;

@Repository
@Transactional
public interface PayrollEmployeeRepository extends JpaRepository<PayrollEmployee, Integer> {
	
	@Query(value="select * from `employee`",nativeQuery = true)
	List<PayrollEmployee> findAll();

}
