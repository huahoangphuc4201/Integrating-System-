package com.dashboard.servicesimp;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.entities.DashboardBenefitPlans;
import com.dashboard.entities.DashboardEmergencyContacts;
import com.dashboard.entities.DashboardEmployee;
import com.dashboard.entities.DashboardEmployment;
import com.dashboard.entities.DashboardJobHistory;
import com.dashboard.entities.DashboardPayrates;
import com.dashboard.entities.DashboardPersonal;
import com.dashboard.hrm.entities.HRMBenefitPlans;
import com.dashboard.hrm.entities.HRMEmergencyContacts;
import com.dashboard.hrm.entities.HRMEmployment;
import com.dashboard.hrm.entities.HRMJobHistory;
import com.dashboard.hrm.entities.HRMPersonal;
import com.dashboard.hrm.repositories.HRMBenefitPlansRepository;
import com.dashboard.hrm.repositories.HRMEmergencyContactsRepository;
import com.dashboard.hrm.repositories.HRMEmploymentRepository;
import com.dashboard.hrm.repositories.HRMJobHistoryRepository;
import com.dashboard.hrm.repositories.HRMPersonalRepository;
import com.dashboard.payroll.entities.PayrollEmployee;
import com.dashboard.payroll.entities.PayrollPayrates;
import com.dashboard.payroll.repositories.PayrollEmployeeRepository;
import com.dashboard.payroll.repositories.PayrollPayratesRepository;
import com.dashboard.repositories.DashboardBenefitPlansRepository;
import com.dashboard.repositories.DashboardEmergencyContactsRepository;
import com.dashboard.repositories.DashboardEmployeeRepository;
import com.dashboard.repositories.DashboardEmploymentRepository;
import com.dashboard.repositories.DashboardJobHistoryRepository;
import com.dashboard.repositories.DashboardPayratesRepository;
import com.dashboard.repositories.DashboardPersonalRepository;
import com.dashboard.services.DashboardPersonalService;

@Service
public class DashboardPersonalServiceImp implements DashboardPersonalService {

	@Autowired
	private DashboardPersonalRepository dashboardPersonalRepo;

	@Autowired
	private PayrollEmployeeRepository payrollEmployeeRepo;

	@Autowired
	private PayrollPayratesRepository payrollPayratesRepo;

	@Autowired
	private DashboardPayratesRepository dashboardPayratesRepo;

	@Autowired
	private DashboardEmployeeRepository dashboardEmployeeRepo;

	@Autowired
	private DashboardBenefitPlansRepository dashboardBenefitPlansRepo;

	@Autowired
	private HRMBenefitPlansRepository hrmBenefitPlansRepo;

	@Autowired
	private HRMEmploymentRepository hrmEmploymentRepo;

	@Autowired
	private DashboardEmploymentRepository dashboardEmploymentRepo;

	@Autowired
	private HRMEmergencyContactsRepository hrmEmergencyContactsRepo;

	@Autowired
	private DashboardEmergencyContactsRepository dashboardEmergencyContactsRepo;

	@Autowired
	private HRMPersonalRepository hrmPersonalRepo;

	@Autowired
	private HRMJobHistoryRepository hrmJobHistoryRepo;

	@Autowired
	private DashboardJobHistoryRepository dashboardJobHistoryRepo;

	@Override
	public DashboardPersonal findByEmployeeId(long id) {
		return dashboardPersonalRepo.findByEmployeeId(id);
	}

	@Override
	public void synchronize() {
		synchronizeHRM();
		synchronizePayroll();
	}

	private void synchronizePayroll() {
		for (PayrollPayrates item : payrollPayratesRepo.findAll()) {
			DashboardPayrates dashboardPayrates = new DashboardPayrates(item.getPayratesId(), null,
					item.getPayRateName(), item.getValue(), item.getTaxPercentage(), item.getPayType(),
					item.getPayAmount(), item.getLevelC());
			dashboardPayratesRepo.save(dashboardPayrates);
		}

		for (PayrollEmployee item : payrollEmployeeRepo.findAll()) {
			long id = item.getEmployeeId();
			DashboardEmployee dashboardEmployee = new DashboardEmployee(id,
					dashboardPayratesRepo.findByPayrateId(item.getPayRates().getPayratesId()), null, item.getPayRate(),
					item.getVacationDays(), item.getPaidToDate(), item.getPaidLastYear());
			dashboardEmployeeRepo.save(dashboardEmployee);
		}
	}

	private void synchronizeHRM() {
		List<String> messages= new ArrayList<String>();
		for (HRMBenefitPlans item : hrmBenefitPlansRepo.findAll()) {
			DashboardBenefitPlans dashboardBenefitPlans = new DashboardBenefitPlans(item.getBenefitPlanId(), null,
					item.getPlanName(), item.getDeductable(), item.getPercentage());
			dashboardBenefitPlansRepo.save(dashboardBenefitPlans);
		}

		for (HRMEmergencyContacts item : hrmEmergencyContactsRepo.findAll()) {
			DashboardEmergencyContacts dashboardEmergencyContacts = new DashboardEmergencyContacts(item.getEmployeeId(),
					null, item.getEmergencyContactName(), item.getPhoneNumber(), item.getRelationship());
			dashboardEmergencyContactsRepo.save(dashboardEmergencyContacts);
		}

		for (HRMEmployment item : hrmEmploymentRepo.findAll()) {
			DashboardEmployment dashboardEmployment = new DashboardEmployment(item.getEmployeeId(), null,
					item.getEmploymentStatus(), item.getHireDate(), item.getWorkersCompCode(),
					item.getTerminationDate(), item.getRehireDate(), item.getLastReviewDate());
			dashboardEmploymentRepo.save(dashboardEmployment);

		}

		for (HRMPersonal item : hrmPersonalRepo.findAll()) {
			long id = item.getEmployeeId();
			DashboardBenefitPlans dashboardBenefitPlans = dashboardBenefitPlansRepo
					.findByBenefitPlansId(item.getBenefitPlans().getBenefitPlanId());
			DashboardEmployment dashboardEmployment = dashboardEmploymentRepo.findByEmploymentId(id);
			DashboardEmergencyContacts dashboardEmergencyContacts = dashboardEmergencyContactsRepo
					.findByEmergencyContactsId(id);
			DashboardEmployee dashboardEmployee = dashboardEmployeeRepo.findByEmployeeId(id);

			DashboardPersonal temp= dashboardPersonalRepo.findByEmployeeId(id);
			if(temp!=null) {
				temp.setAddress1(item.getAddress1());
				temp.setAddress2(item.getAddress2());
				temp.setCity(item.getCity());
				if(temp.getBenefitPlans().getBenefitPlanId()!=dashboardBenefitPlans.getBenefitPlanId()) {
					String message= temp.getFirstName()+" "+temp.getFirstName()+" has changed benefit from "+temp.getBenefitPlans().getPlanName()+" to "+dashboardBenefitPlans.getPlanName();
					temp.setBenefitPlans(dashboardBenefitPlans);
					messages.add(message);
				}
				temp.setDriversLicense(item.getDriversLicense());
				temp.setEmail(item.getEmail());
				temp.setEmergencyContacts(dashboardEmergencyContacts);
				temp.setEmployee(dashboardEmployee);
				temp.setEmployment(dashboardEmployment);
				temp.setEthnicity(item.getEthnicity());
				temp.setPhoneNumber(item.getPhoneNumber());
				temp.setSocialSecurityNumber(item.getSocialSecurityNumber());
				temp.setZip(item.getZip());
			}
			else {
			DashboardPersonal dashboardPersonal = new DashboardPersonal(id, dashboardBenefitPlans, null,
					dashboardEmergencyContacts, dashboardEmployment, null, dashboardEmployee, item.getFirstName(),
					item.getLastName(), item.getMiddleInitial(), item.getAddress1(), item.getAddress2(), item.getCity(),
					item.getState(), item.getZip(), item.getEmail(), item.getPhoneNumber(),
					item.getSocialSecurityNumber(), item.getDriversLicense(), item.getMaritalStatus(), item.getGender(),
					item.getShareholderStatus(), item.getEthnicity());
			dashboardPersonalRepo.save(dashboardPersonal);
			}
		}

		for (HRMJobHistory item : hrmJobHistoryRepo.findAll()) {
			DashboardJobHistory dashboardJobHistory = new DashboardJobHistory(item.getId(),
					dashboardPersonalRepo.findByEmployeeId(item.getPersonal().getEmployeeId()), item.getDepartment(),
					item.getDivision(), item.getStartDate(), item.getEndDate(), item.getSupervisor(),
					item.getJobTitle(), item.getJobCategory(), item.getLocation(), item.getDepartmentCode(),
					item.getSalaryType(), item.getPayPeriod(), item.getHoursPerWeek(), item.getHazardousTraining());
			dashboardJobHistoryRepo.save(dashboardJobHistory);
		}

	}

	@Override
	public List<Map<String, String>> findAllEmployeeInfo() {
		List<DashboardPersonal> personals = dashboardPersonalRepo.findAll();
		List<Map<String, String>> array = new ArrayList<Map<String, String>>();
		for (DashboardPersonal personal : personals) {
			Map<String, String> object = new HashMap<String, String>();
			object.put("employeeID", personal.getEmployeeId() + "");
			object.put("firstname", personal.getFirstName());
			object.put("lastname", personal.getLastName());
			object.put("address1", personal.getAddress1());
			object.put("address2", personal.getAddress2());
			object.put("city", personal.getCity());
			object.put("driversLicense", personal.getDriversLicense());
			object.put("email", personal.getEmail());
			object.put("ethnicity", personal.getEthnicity());
			object.put("gender", personal.getGender() ? "Male" : "Female");
			object.put("marital", personal.getMaritalStatus());
			object.put("middleInitial", personal.getMiddleInitial());
			object.put("phoneNumber", personal.getPhoneNumber());
			object.put("shareholder", personal.getShareholderStatus() ? "Yes" : "No");
			object.put("ssn", personal.getSocialSecurityNumber());
			object.put("zip", personal.getZip() + "");
			object.put("state", personal.getState());
			object.put("planName", personal.getBenefitPlans().getPlanName());
			object.put("percentageCoPay", personal.getBenefitPlans().getPercentage() + "");
			object.put("deductable", personal.getBenefitPlans().getDeductable() + "");
			object.put("paidLastYear", personal.getEmployee().getPaidLastYear() + "");
			object.put("paidToDate", personal.getEmployee().getPaidToDate() + "");
			object.put("vacationDays", personal.getEmployee().getVacationDays() + "");
			object.put("department", personal.getJobHistories().get(0).getDepartment());
			object.put("payRateName", personal.getEmployee().getPayRates().getPayRateName());
			object.put("levelC", personal.getEmployee().getPayRates().getLevelC() + "");
			object.put("payAmount", personal.getEmployee().getPayRates().getPayAmount() + "");
			object.put("payType", personal.getEmployee().getPayRates().getPayType() + "");
			object.put("taxPercentage", personal.getEmployee().getPayRates().getTaxPercentage() + "");
			object.put("value", personal.getEmployee().getPayRates().getValue() + "");
			array.add(object);
		}
		return array;
	}

	@Override
	public List<DashboardPersonal> findAll() {
		return dashboardPersonalRepo.findAll();
	}

	@Override
	public List<Map<String, String>> getAccount() {
		List<DashboardPersonal> personals = dashboardPersonalRepo.findAll();
		List<Map<String, String>> array = new ArrayList<Map<String, String>>();
		for (DashboardPersonal personal : personals) {
			Map<String, String> object = new HashMap<String, String>();
			object.put("employeeID", personal.getEmployeeId() + "");
			object.put("firstname", personal.getFirstName());
			object.put("lastname", personal.getLastName());
			if (personal.getUser() != null) {
				object.put("username", personal.getUser().getUsername());
				object.put("role", personal.getUser().getRole().getName());
			} else {
				object.put("username", null);
				object.put("role", null);
			}
			array.add(object);
		}
		return array;
	}

	@Override
	public Boolean updateProfile(long id, String address, String city, String email, String phone, long zip,
			String ssn) {
		DashboardPersonal personal = dashboardPersonalRepo.findByEmployeeId(id);
		personal.setAddress1(address);
		personal.setCity(city);
		personal.setEmail(email);
		personal.setPhoneNumber(phone);
		personal.setZip(zip);
		personal.setSocialSecurityNumber(ssn);
		HRMPersonal personal2 = hrmPersonalRepo.findByEmployeeId(id);
		personal2.setAddress1(address);
		personal2.setCity(city);
		personal2.setEmail(email);
		personal2.setPhoneNumber(phone);
		personal2.setZip(zip);
		personal2.setSocialSecurityNumber(ssn);
		if (dashboardPersonalRepo.save(personal) != null && hrmPersonalRepo.save(personal2) != null)
			return true;
		return false;
	}
	@Override
	public List<Map<String,String>> getEmployeeOverDayoff(){
		List<Map<String,String>> dayoffAlert= new ArrayList<Map<String,String>>();
		List<DashboardPersonal> personals = findAll();
		for (DashboardPersonal dashboardPersonal : personals) {
			int dayoff = dashboardPersonal.getEmployee().getVacationDays();
			if(dayoff>15) {
				Map<String,String> object= new HashMap<String, String>();
				object.put("count", dayoff+" / "+15);
				object.put("message",dashboardPersonal.getFirstName()+" "+dashboardPersonal.getLastName()+" - Over "+(dayoff-15)+" day(s)");
				dayoffAlert.add(object);
			}
		}
		return dayoffAlert;
	}
	
	@Override
	public List<Map<String,String>> getAnninersaryInMonth(){
		List<Map<String,String>> anniversary= new ArrayList<Map<String,String>>();
		List<DashboardPersonal> personals = findAll();
		for (DashboardPersonal dashboardPersonal : personals) {
			Date date=new Date(dashboardPersonal.getEmployment().getHireDate().getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.MONTH)==Calendar.getInstance().get(Calendar.MONTH)&&calendar.get(Calendar.DAY_OF_MONTH)>=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
				Map<String,String> object= new HashMap<String, String>();
				object.put("date",calendar.get(Calendar.DAY_OF_MONTH)+", "+LocalDate.now().getMonth());
				object.put("event", dashboardPersonal.getFirstName()+" "+dashboardPersonal.getLastName()+" - Hiring anniversary");
				anniversary.add(object);
			}
		}
		return anniversary;
	}
}
