package com.dashboard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class DashboardEmployee {

	@Id
	@Column(name = "Employee_ID")
	private Long employeeId;
	
	//employee - pay_rate
	@ManyToOne
    @JoinColumn(name="Pay_Rate_ID")
    private DashboardPayrates payRates;
	
	//employee - personal
	@OneToOne
	@PrimaryKeyJoinColumn(name = "Employee_ID")
    private DashboardPersonal personal;
	
	@Column(name = "Pay_Rate")
	private String payRate;
	
	@Column(name = "Vacation_Days")
	private int vacationDays;
	
	@Column(name = "Paid_To_Date")
	private long paidToDate;
	
	@Column(name = "Paid_Last_Year")
	private long paidLastYear;

	public DashboardEmployee() {}
	
	public DashboardEmployee(Long employeeId, DashboardPayrates payRates, DashboardPersonal personal, String payRate,
			int vacationDays, long paidToDate, long paidLastYear) {
		super();
		this.employeeId = employeeId;
		this.payRates = payRates;
		this.personal = personal;
		this.payRate = payRate;
		this.vacationDays = vacationDays;
		this.paidToDate = paidToDate;
		this.paidLastYear = paidLastYear;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public DashboardPayrates getPayRates() {
		return payRates;
	}

	public void setPayRates(DashboardPayrates payRates) {
		this.payRates = payRates;
	}

	public DashboardPersonal getPersonal() {
		return personal;
	}

	public void setPersonal(DashboardPersonal personal) {
		this.personal = personal;
	}

	public String getPayRate() {
		return payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}

	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	public long getPaidToDate() {
		return paidToDate;
	}

	public void setPaidToDate(long paidToDate) {
		this.paidToDate = paidToDate;
	}

	public long getPaidLastYear() {
		return paidLastYear;
	}

	public void setPaidLastYear(long paidLastYear) {
		this.paidLastYear = paidLastYear;
	}
	
	
	
}
