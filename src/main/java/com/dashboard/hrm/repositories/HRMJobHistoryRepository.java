package com.dashboard.hrm.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.hrm.entities.HRMJobHistory;

@Repository
@Transactional
public interface HRMJobHistoryRepository extends JpaRepository<HRMJobHistory, Long> {

}
