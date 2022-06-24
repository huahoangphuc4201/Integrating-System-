package com.dashboard.hrm.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.hrm.entities.HRMEmergencyContacts;

@Repository
@Transactional
public interface HRMEmergencyContactsRepository extends JpaRepository<HRMEmergencyContacts, Long> {

}
