package com.dashboard.hrm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Employment")
public class HRMEmployment {

	@Id
	@Column(name = "Employee_ID")
	private Long employeeId;
	
	//employment - personal
	@OneToOne
	@PrimaryKeyJoinColumn(name = "Employee_ID")
	private HRMPersonal personal;
	
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
