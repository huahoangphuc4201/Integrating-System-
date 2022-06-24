package com.dashboard.hrm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Emergency_Contacts")
public class HRMEmergencyContacts {

	@Id
	@Column(name = "Employee_ID")
	private Long employeeId;
	
	//emergency_contacts - personal
	@OneToOne
	@PrimaryKeyJoinColumn(name = "Employee_ID")
	private HRMPersonal personal;
	
	@Column(name = "Emergency_Contact_Name")
	private String emergencyContactName;
	
	@Column(name = "Phone_Number")
	private String phoneNumber;
	
	@Column(name = "Relationship")
	private String relationship;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public HRMPersonal getPersonal() {
		return personal;
	}

	public void setPersonal(HRMPersonal personal) {
		this.personal = personal;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
}
