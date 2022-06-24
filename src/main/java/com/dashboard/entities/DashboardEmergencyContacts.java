package com.dashboard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Emergency_Contacts")
public class DashboardEmergencyContacts {

	@Id
	@Column(name = "Employee_ID")
	private Long employeeId;
	
	//emergency_contacts - personal
	@OneToOne
	@PrimaryKeyJoinColumn(name = "Employee_ID")
	private DashboardPersonal personal;
	
	@Column(name = "Emergency_Contact_Name")
	private String emergencyContactName;
	
	@Column(name = "Phone_Number")
	private String phoneNumber;
	
	@Column(name = "Relationship")
	private String relationship;

	public long getEmployeeId() {
		return employeeId;
	}

	public DashboardEmergencyContacts() {}
	
	public DashboardEmergencyContacts(Long employeeId, DashboardPersonal personal, String emergencyContactName,
			String phoneNumber, String relationship) {
		super();
		this.employeeId = employeeId;
		this.personal = personal;
		this.emergencyContactName = emergencyContactName;
		this.phoneNumber = phoneNumber;
		this.relationship = relationship;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public DashboardPersonal getPersonal() {
		return personal;
	}

	public void setPersonal(DashboardPersonal personal) {
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
