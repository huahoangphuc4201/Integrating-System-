package com.dashboard.payroll.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class PayrollEmployee {

	@Id
	@Column(name = "Employee Number")
	private Integer employeeId;
	
	//employee - pay_rates
	@ManyToOne
    @JoinColumn(name="Pay Rates_idPay Rates")
    private PayrollPayrates payRates;
	
	@Column(name = "Last Name")
	private String lastName;
	
	@Column(name = "First Name")
	private String firstName;
	
	@Column(name = "SSN")
	private long ssn;
	
	@Column(name = "Pay Rate")
	private String payRate;
	
	@Column(name = "Vacation Days")
	private int vacationDays;
	
	@Column(name = "Paid To Date")
	private long paidToDate;
	
	@Column(name = "Paid Last Year")
	private long paidLastYear;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public PayrollPayrates getPayRates() {
		return payRates;
	}

	public void setPayRates(PayrollPayrates payRates) {
		this.payRates = payRates;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
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
