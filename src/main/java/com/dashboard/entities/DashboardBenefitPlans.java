package com.dashboard.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Benefit_Plans")
public class DashboardBenefitPlans {

	@Id
	@Column(name="Benefit_Plan_ID")
	private Long benefitPlanId;
	
	//benefit_plans - personal
	@OneToMany(mappedBy = "benefitPlans")
	private Set<DashboardPersonal> personals;
	
	@Column(name="Plan_name")
	private String planName;
	
	@Column(name = "Deductable")
	private long deductable;
	
	@Column(name = "Percentage_CoPay")
	private int percentage;

	public Long getBenefitPlanId() {
		return benefitPlanId;
	}

	public DashboardBenefitPlans() {}
	
	public DashboardBenefitPlans(Long benefitPlanId, Set<DashboardPersonal> personals, String planName, long deductable,
			int percentage) {
		super();
		this.benefitPlanId = benefitPlanId;
		this.personals = personals;
		this.planName = planName;
		this.deductable = deductable;
		this.percentage = percentage;
	}

	public void setBenefitPlanId(Long benefitPlanId) {
		this.benefitPlanId = benefitPlanId;
	}

	public Set<DashboardPersonal> getPersonals() {
		return personals;
	}

	public void setPersonals(Set<DashboardPersonal> personals) {
		this.personals = personals;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public long getDeductable() {
		return deductable;
	}

	public void setDeductable(long deductable) {
		this.deductable = deductable;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	
}
