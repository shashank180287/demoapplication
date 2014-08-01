package com.mobile.tool.stock.manager.entity;

import java.sql.Date;

public class EmployeeQualificationDetails {

	private int id;
	private EmployeeRecord empolyee;
	private String degreeName;
	private String institutionName;
	private Date startDate;
	private Date endDate;
	private double percentage;
	public EmployeeQualificationDetails(int id, EmployeeRecord empolyee,
			String degreeName, String institutionName, Date startDate,
			Date endDate, double percentage) {
		super();
		this.id = id;
		this.empolyee = empolyee;
		this.degreeName = degreeName;
		this.institutionName = institutionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.percentage = percentage;
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
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
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
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "EmployeeQualificationDetails [id=" + id + ", empolyee="
				+ empolyee + ", degreeName=" + degreeName
				+ ", institutionName=" + institutionName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", percentage="
				+ percentage + "]";
	}
	
}
