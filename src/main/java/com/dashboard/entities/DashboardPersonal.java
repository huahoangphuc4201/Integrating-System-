package com.dashboard.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = "Personal")
public class DashboardPersonal {

	@Id
	@Column(name="Employee_ID",nullable = false)
	private Long employeeId;
	
	//personal - benefit_plans
	@ManyToOne
	@JoinColumn(name = "Benefit_Plans")
	private DashboardBenefitPlans benefitPlans;
	
	//personal - user
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Employee_ID")
    private User user;
	
	//personal - emergency_contacts
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Employee_ID")
    private DashboardEmergencyContacts emergencyContacts;
	
	//personal - employment
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Employee_ID")
	private DashboardEmployment employment ;
	
	//personal - job_history
	@OneToMany(fetch = FetchType.EAGER,mappedBy="personal")
    private List<DashboardJobHistory> jobHistories;
	
	//personal - employee
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Employee_ID")
    private DashboardEmployee employee;
	
	public DashboardPersonal() {}
	
	public DashboardPersonal(Long employeeId, DashboardBenefitPlans benefitPlans, User user,
			DashboardEmergencyContacts emergencyContacts, DashboardEmployment employment,
			List<DashboardJobHistory> jobHistories, DashboardEmployee employee, String firstName, String lastName,
			String middleInitial, String address1, String address2, String city, String state, long zip, String email,
			String phoneNumber, String socialSecurityNumber, String driversLicense, String maritalStatus,
			Boolean gender, Boolean shareholderStatus, String ethnicity) {
		super();
		this.employeeId = employeeId;
		this.benefitPlans = benefitPlans;
		this.user = user;
		this.emergencyContacts = emergencyContacts;
		this.employment = employment;
		this.jobHistories = jobHistories;
		this.employee = employee;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.socialSecurityNumber = socialSecurityNumber;
		this.driversLicense = driversLicense;
		this.maritalStatus = maritalStatus;
		this.gender = gender;
		this.shareholderStatus = shareholderStatus;
		this.ethnicity = ethnicity;
	}

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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public DashboardBenefitPlans getBenefitPlans() {
		return benefitPlans;
	}

	public void setBenefitPlans(DashboardBenefitPlans benefitPlans) {
		this.benefitPlans = benefitPlans;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DashboardEmergencyContacts getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(DashboardEmergencyContacts emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public DashboardEmployment getEmployment() {
		return employment;
	}

	public void setEmployment(DashboardEmployment employment) {
		this.employment = employment;
	}

	public List<DashboardJobHistory> getJobHistories() {
		return jobHistories;
	}

	public void setJobHistories(List<DashboardJobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public DashboardEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(DashboardEmployee employee) {
		this.employee = employee;
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
