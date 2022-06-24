package com.dashboard.hrm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Job_History")
public class HRMJobHistory {

	@Id
	@Column(name = "ID")
	private Long id;
	
	//job_history - personal
	@ManyToOne
    @JoinColumn(name="Employee_ID", nullable=false)
	private HRMPersonal personal;
	
	@Column(name = "Department")
	private String department;
	
	@Column(name = "Division")
	private String division;
	
	@Column(name = "Start_Date")
	private Date startDate;
	
	@Column(name = "End_Date")
	private Date endDate;
	
	@Column(name = "Supervisor")
	private long supervisor;
	
	@Column(name = "Job_Title")
	private String jobTitle;
	
	@Column(name = "Job_Category")
	private String jobCategory;
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "Department_Code")
	private long departmentCode;
	
	@Column(name = "Salary_Type")
	private long salaryType;
	
	@Column(name = "Pay_Period")
	private String payPeriod;
	
	@Column(name = "Hours_per_Week")
	private long hoursPerWeek;
	
	@Column(name = "Hazardous_Training")
	private Boolean hazardousTraining;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HRMPersonal getPersonal() {
		return personal;
	}

	public void setPersonal(HRMPersonal personal) {
		this.personal = personal;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(long supervisor) {
		this.supervisor = supervisor;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(long departmentCode) {
		this.departmentCode = departmentCode;
	}

	public long getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(long salaryType) {
		this.salaryType = salaryType;
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public long getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(long hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public Boolean getHazardousTraining() {
		return hazardousTraining;
	}

	public void setHazardousTraining(Boolean hazardousTraining) {
		this.hazardousTraining = hazardousTraining;
	}
	
}
