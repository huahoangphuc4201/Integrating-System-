package com.dashboard.payroll.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pay rates")
public class PayrollPayrates {

	@Id
	@Column(name = "idPay Rates")
	private Integer payratesId;
	
	//pay_rates - employee
	@OneToMany(mappedBy="payRates")
    private Set<PayrollEmployee> employees;
	
	@Column(name = "Pay Rate Name")
	private String payRateName;
	
	@Column(name = "Value")
	private long value;
	
	@Column(name = "Tax Percentage")
	private long taxPercentage;
	
	@Column(name = "Pay Type")
	private int payType;
	
	@Column(name = "Pay Amount")
	private long payAmount;
	
	@Column(name = "PT - Level C")
	private long levelC;

	public Integer getPayratesId() {
		return payratesId;
	}

	public void setPayratesId(Integer payratesId) {
		this.payratesId = payratesId;
	}

	public Set<PayrollEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<PayrollEmployee> employees) {
		this.employees = employees;
	}

	public String getPayRateName() {
		return payRateName;
	}

	public void setPayRateName(String payRateName) {
		this.payRateName = payRateName;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public long getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(long taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}

	public long getLevelC() {
		return levelC;
	}

	public void setLevelC(long levelC) {
		this.levelC = levelC;
	}
	
}
