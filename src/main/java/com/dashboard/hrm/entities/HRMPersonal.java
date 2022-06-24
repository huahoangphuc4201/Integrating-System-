package com.dashboard.hrm.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Personal")
public class HRMPersonal {

	@Id
	@Column(name="Employee_ID")
	private Long employeeId;
	
	//personal - benefit_plans
	@ManyToOne
	@JoinColumn(name = "Benefit_Plans")
	private HRMBenefitPlans benefitPlans;
	
	//personal - emergency_contacts
	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private HRMEmergencyContacts emergencyContacts;
	
	//personal - employment
	@OneToOne(mappedBy = "personal", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private HRMEmployment employment ;
	
	//personal - job_history
	@OneToMany(mappedBy="personal")
	private Set<HRMJobHistory> jobHistories;
	
	@Column(name="First_Name")
	private String firstName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Middle_Initial")
	private String middleInitial;

	@Column(name="Address1")
	private String address1;
	
	@Column(name="Address2")
	private String address2;
	
	@Column(name="City")
	private String city;
	
	@Column(name="State")
	private String state;
	
	@Column(name = "Zip")
	private long zip;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Phone_Number")
	private String phoneNumber;
	
	@Column(name="Social_Security_Number")
	private String socialSecurityNumber;
	
	@Column(name="Drivers_License")
	private String driversLicense;
	
	@Column(name="Marital_Status")
	private String maritalStatus;
	
	@Column(name = "Gender")
	private Boolean gender;
	
	@Column(name = "Shareholder_Status")
	private Boolean shareholderStatus;
	
	@Column(name="Ethnicity")
	private String ethnicity;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public HRMBenefitPlans getBenefitPlans() {
		return benefitPlans;
	}

	public void setBenefitPlans(HRMBenefitPlans benefitPlans) {
		this.benefitPlans = benefitPlans;
	}

	public HRMEmergencyContacts getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(HRMEmergencyContacts emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public HRMEmployment getEmployment() {
		return employment;
	}

	public void setEmployment(HRMEmployment employment) {
		this.employment = employment;
	}

	public Set<HRMJobHistory> getJobHistories() {
		return jobHistories;
	}

	public void setJobHistories(Set<HRMJobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getDriversLicense() {
		return driversLicense;
	}

	public void setDriversLicense(String driversLicense) {
		this.driversLicense = driversLicense;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Boolean getShareholderStatus() {
		return shareholderStatus;
	}

	public void setShareholderStatus(Boolean shareholderStatus) {
		this.shareholderStatus = shareholderStatus;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	
}
