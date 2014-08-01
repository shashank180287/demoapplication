package com.mobile.tool.stock.manager.entity;

import java.sql.Date;

public class EmployeeProfessionalDetails {

	private int id;
	private EmployeeRecord empolyee;
	private  String jobTitle;
	private  String companyName;
	private  Date startDate;
	private  Date endDate;
	private String responsobilities;
	
	public EmployeeProfessionalDetails(int id, EmployeeRecord empolyee,
			String jobTitle, String companyName, Date startDate, Date endDate,
			String responsobilities) {
		super();
		this.id = id;
		this.empolyee = empolyee;
		this.jobTitle = jobTitle;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.responsobilities = responsobilities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployeeRecord getEmpolyee() {
		return empolyee;
	}

	public void setEmpolyee(EmployeeRecord empolyee) {
		this.empolyee = empolyee;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getResponsobilities() {
		return responsobilities;
	}

	public void setResponsobilities(String responsobilities) {
		this.responsobilities = responsobilities;
	}

	@Override
	public String toString() {
		return "EmployeeProfessionalDetails [id=" + id + ", empolyee="
				+ empolyee + ", jobTitle=" + jobTitle + ", companyName="
				+ companyName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", responsobilities=" + responsobilities + "]";
	}

}
