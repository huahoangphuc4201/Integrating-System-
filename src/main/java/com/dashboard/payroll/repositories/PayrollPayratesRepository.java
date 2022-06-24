package com.dashboard.payroll.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dashboard.payroll.entities.PayrollPayrates;

@Repository
public interface PayrollPayratesRepository extends JpaRepository<PayrollPayrates, Integer> {
	@Query(value="select * from `pay rates`",nativeQuery = true)
	List<PayrollPayrates> findAll();

}
