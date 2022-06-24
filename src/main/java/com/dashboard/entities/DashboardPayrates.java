package com.dashboard.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Pay_Rate")
public class DashboardPayrates {

	@Id
	@Column(name = "Pay_Rate_ID")
	private Integer payRateId;
	
	//pay_rate - employee 
	@OneToMany(mappedBy="payRates")
    private Set<DashboardEmployee> employees;
	
	@Column(name = "Pay_Rate_Name")
	private String payRateName;
	
	@Column(name = "Value")
	private long value;
	
	@Column(name = "Tax_Percentage")
	private long taxPercentage;
	
	@Column(name = "Pay_Type")
	private int payType;
	
	@Column(name = "Pay_Amount")
	private long payAmount;
	
	@Column(name = "PT_LevelC")
	private long levelC;

	public DashboardPayrates() {}
	
	public DashboardPayrates(Integer payRateId, Set<DashboardEmployee> employees, String payRateName, long value,
			long taxPercentage, int payType, long payAmount, long levelC) {
		super();
		this.payRateId = payRateId;
		this.employees = employees;
		this.payRateName = payRateName;
		this.value = value;
		this.taxPercentage = taxPercentage;
		this.payType = payType;
		this.payAmount = payAmount;
		this.levelC = levelC;
	}

	public Integer getPayRateId() {
		return payRateId;
	}

	public void setPayRateId(Integer payRateId) {
		this.payRateId = payRateId;
	}

	public Set<DashboardEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<DashboardEmployee> employees) {
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
