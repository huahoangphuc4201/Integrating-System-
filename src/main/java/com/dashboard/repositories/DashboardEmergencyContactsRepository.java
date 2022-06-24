package com.dashboard.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dashboard.entities.DashboardEmergencyContacts;

@Repository
@Transactional
public interface DashboardEmergencyContactsRepository extends JpaRepository<DashboardEmergencyContacts, Long> {
	@Query(value="select * from Emergency_Contacts as u where u.Employee_ID=:id",nativeQuery = true)
	DashboardEmergencyContacts findByEmergencyContactsId(@Param("id")long id);
}
