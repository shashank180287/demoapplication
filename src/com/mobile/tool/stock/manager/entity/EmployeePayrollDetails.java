package com.mobile.tool.stock.manager.entity;

import java.sql.Date;

public class EmployeePayrollDetails {
	private int id;
	private EmployeeRecord employeeRecord;
	private String department;
	private double sickLeaves;
	private double casualLeaves;
	private double annualLeaves;
	private double studyLeaves;
	private double other;
	private double daysOfLeaves;
	private Date leaveStartDate;
	private Date leaveEndDate;
	private String loanName;
	private double intertestRate;
	private String gradeLevel;
	private double allowances;
	private double initialSalary;
	private double upgradeSalary;
	private String employmentType; // Full Time, Contract

<<<<<<< HEAD
	public static String[] attributeColumnNames = new String[]{"Attribute","Value"};
	
=======
>>>>>>> origin/master
	public EmployeePayrollDetails(int id, EmployeeRecord employeeRecord,
			String department, double sickLeaves, double casualLeaves,
			double annualLeaves, double studyLeaves, double other,
			double daysOfLeaves, Date leaveStartDate, Date leaveEndDate,
			String loanName, double intertestRate, String gradeLevel,
			double allowances, double initialSalary, double upgradeSalary,
			String employmentType) {
		super();
		this.id = id;
		this.employeeRecord = employeeRecord;
		this.department = department;
		this.sickLeaves = sickLeaves;
		this.casualLeaves = casualLeaves;
		this.annualLeaves = annualLeaves;
		this.studyLeaves = studyLeaves;
		this.other = other;
		this.daysOfLeaves = daysOfLeaves;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.loanName = loanName;
		this.intertestRate = intertestRate;
		this.gradeLevel = gradeLevel;
		this.allowances = allowances;
		this.initialSalary = initialSalary;
		this.upgradeSalary = upgradeSalary;
		this.employmentType = employmentType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployeeRecord getEmployeeRecord() {
		return employeeRecord;
	}

	public void setEmployeeRecord(EmployeeRecord employeeRecord) {
		this.employeeRecord = employeeRecord;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSickLeaves() {
		return sickLeaves;
	}

	public void setSickLeaves(double sickLeaves) {
		this.sickLeaves = sickLeaves;
	}

	public double getCasualLeaves() {
		return casualLeaves;
	}

	public void setCasualLeaves(double casualLeaves) {
		this.casualLeaves = casualLeaves;
	}

	public double getAnnualLeaves() {
		return annualLeaves;
	}

	public void setAnnualLeaves(double annualLeaves) {
		this.annualLeaves = annualLeaves;
	}

	public double getStudyLeaves() {
		return studyLeaves;
	}

	public void setStudyLeaves(double studyLeaves) {
		this.studyLeaves = studyLeaves;
	}

	public double getOther() {
		return other;
	}

	public void setOther(double other) {
		this.other = other;
	}

	public double getDaysOfLeaves() {
		return daysOfLeaves;
	}

	public void setDaysOfLeaves(double daysOfLeaves) {
		this.daysOfLeaves = daysOfLeaves;
	}

	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public double getIntertestRate() {
		return intertestRate;
	}

	public void setIntertestRate(double intertestRate) {
		this.intertestRate = intertestRate;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public double getAllowances() {
		return allowances;
	}

	public void setAllowances(double allowances) {
		this.allowances = allowances;
	}

	public double getInitialSalary() {
		return initialSalary;
	}

	public void setInitialSalary(double initialSalary) {
		this.initialSalary = initialSalary;
	}

	public double getUpgradeSalary() {
		return upgradeSalary;
	}

	public void setUpgradeSalary(double upgradeSalary) {
		this.upgradeSalary = upgradeSalary;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	@Override
	public String toString() {
		return "EmployeePayrollDetails [id=" + id + ", employeeRecord="
				+ employeeRecord + ", department=" + department
				+ ", sickLeaves=" + sickLeaves + ", casualLeaves="
				+ casualLeaves + ", annualLeaves=" + annualLeaves
				+ ", studyLeaves=" + studyLeaves + ", other=" + other
				+ ", daysOfLeaves=" + daysOfLeaves + ", leaveStartDate="
				+ leaveStartDate + ", leaveEndDate=" + leaveEndDate
				+ ", loanName=" + loanName + ", intertestRate=" + intertestRate
				+ ", gradeLevel=" + gradeLevel + ", allowances=" + allowances
				+ ", initialSalary=" + initialSalary + ", upgradeSalary="
				+ upgradeSalary + ", employmentType=" + employmentType + "]";
	}
<<<<<<< HEAD
	
	public static String[][] getTableModel(
			EmployeePayrollDetails employeePayrollDetails) {
			String[][] tableModelArray = new String[][] {
					{"Department", employeePayrollDetails.getDepartment()},
					{"Sick Leaves",	employeePayrollDetails.getSickLeaves()+""},
					{"Casual Leaves", employeePayrollDetails.getCasualLeaves()+""},
					{"Annual Leaves", employeePayrollDetails.getAnnualLeaves()+""},
					{"Study Leaves", employeePayrollDetails.getStudyLeaves()+""},
					{"Other", employeePayrollDetails.getOther()+""},
					{"Days of Leaves", employeePayrollDetails.getDaysOfLeaves()+""},
					{"Leaves Start Date", employeePayrollDetails.getLeaveStartDate().toString()},
					{"Leaves End Date",	employeePayrollDetails.getLeaveEndDate().toString()},
					{"Loan Name",	employeePayrollDetails.getLoanName()},
					{"Interest Rate",	employeePayrollDetails.getIntertestRate()+""},
					{"Grade Level",	employeePayrollDetails.getGradeLevel()},
					{"Allowances",	employeePayrollDetails.getAllowances()+""},
					{"Initial Salary",	employeePayrollDetails.getInitialSalary()+""},
					{"Upgrade Salary",	employeePayrollDetails.getUpgradeSalary()+""},
					{"Employment Type",	employeePayrollDetails.getEmploymentType()}
				};
			return tableModelArray;
		}
=======
>>>>>>> origin/master
}
