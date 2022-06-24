package com.dashboard.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Employment")
public class DashboardEmployment {
	
	@Id
	@Column(name = "Employee_ID",nullable = false)
	private Long employeeId;
	
	//employment - personal
	@OneToOne
	@PrimaryKeyJoinColumn(name = "Employee_ID")
	private DashboardPersonal personal;
	
	@Column(name = "Employment_Status")
	private String employmentStatus;
	
	@Column(name = "Hire_Date")
	private Date hireDate;
	
	@Column(name = "Workers_Comp_Code")
	private String workersCompCode;
	
	@Column(name = "Termination_Date")
	private Date terminationDate;
	
	@Column(name = "Rehire_Date")
	private Date rehireDate;
	
	@Column(name = "Last_Review_Date")
	private Date lastReviewDate;

	public DashboardEmployment() {}
	
	public DashboardEmployment(Long employeeId, DashboardPersonal personal, String employmentStatus, Date hireDate,
			String workersCompCode, Date terminationDate, Date rehireDate, Date lastReviewDate) {
		super();
		this.employeeId = employeeId;
		this.personal = personal;
		this.employmentStatus = employmentStatus;
		this.hireDate = hireDate;
		this.workersCompCode = workersCompCode;
		this.terminationDate = terminationDate;
		this.rehireDate = rehireDate;
		this.lastReviewDate = lastReviewDate;
	}

	public long getEmployeeId() {
		return employeeId;
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

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getWorkersCompCode() {
		return workersCompCode;
	}

	public void setWorkersCompCode(String workersCompCode) {
		this.workersCompCode = workersCompCode;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public Date getRehireDate() {
		return rehireDate;
	}

	public void setRehireDate(Date rehireDate) {
		this.rehireDate = rehireDate;
	}

	public Date getLastReviewDate() {
		return lastReviewDate;
	}

	public void setLastReviewDate(Date lastReviewDate) {
		this.lastReviewDate = lastReviewDate;
	}
	
}
