package com.dashboard.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.DashboardJobHistory;

@Repository
@Transactional
public interface DashboardJobHistoryRepository extends JpaRepository<DashboardJobHistory, Long> {
	

}
