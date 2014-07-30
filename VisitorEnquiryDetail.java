package com.mobile.tool.stock.manager.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VisitorEnquiryDetail {

	private long id;
	private String name;
	private long mobileNumber;
	private String address;
	private String visitPurpose;
	private Date date;
	private String comment;
	public static String[] tableColumnNames= new String[] {"Visitor Name", "Mobile Number", "Address", "Visiting Purpose", "Comment"};
	
	public VisitorEnquiryDetail(long id, String name, long mobileNumber,
			String address, String visitPurpose, Date date, String comment) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.visitPurpose = visitPurpose;
		this.date = date;
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVisitPurpose() {
		return visitPurpose;
	}

	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "VisitorEnquiryDetail [id=" + id + ", name=" + name
				+ ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", visitPurpose=" + visitPurpose + ", date=" + date
				+ ", comment=" + comment + "]";
	}

	public static String[][] getTableModel(List<VisitorEnquiryDetail> visitorEnquiryDetails) {
		List<String[]> tableModel = new ArrayList<String[]>();
		for (VisitorEnquiryDetail visitorEnquiryDetail : visitorEnquiryDetails) {
			String[] visitorEnquiry = new String[]{visitorEnquiryDetail.getName(), visitorEnquiryDetail.getMobileNumber()+"", 
					visitorEnquiryDetail.getAddress(), visitorEnquiryDetail.getVisitPurpose(), visitorEnquiryDetail.getComment()};
			tableModel.add(visitorEnquiry);
		}
		String[][] tableModelArray = new String[tableModel.size()][];
		tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
	
}
