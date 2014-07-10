package com.mobile.tool.stock.manager.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CustomerRecord {

	private String customerCode;
	private String firstName;
	private String lastName;
	private long mobileNumber;
	private String email;
	private String gender;
	private String contactAddress;
	private Date created;
	private String website;
	private String description;
	private UserLoginDetails userLoginDetails;
	public static String[] tableColumnNames = new String[]{"Customer Code","First Name","Last Name","Mobile Number",
		"Email", "Gender", "Contact Address","Created","Website","Description","Username"};
	public static String[] attributeColumnNames = new String[]{"Attribute Name","Attribute Value"};
	
	public CustomerRecord() {
	}
	
	public CustomerRecord(String customerCode, String firstName,
			String lastName, long mobileNumber, String email, String gender,
			String contactAddress, Date created, String website,
			String description, UserLoginDetails userLoginDetails) {
		super();
		this.customerCode = customerCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.gender = gender;
		this.contactAddress = contactAddress;
		this.created = created;
		this.website = website;
		this.description = description;
		this.userLoginDetails = userLoginDetails;
	}


	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserLoginDetails getUserLoginDetails() {
		return userLoginDetails;
	}
	public void setUserLoginDetails(UserLoginDetails userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}
	@Override
	public String toString() {
		return "CustomerRecord [customerCode=" + customerCode + ", firstName="
				+ firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", email=" + email + ", gender=" + gender
				+ ", contact_address=" + contactAddress + ", created="
				+ created + ", website=" + website + ", description="
				+ description + ", userLoginDetails=" + userLoginDetails + "]";
	}
	
	public static String[][] getTableModel(List<CustomerRecord> customerRecords) {
		List<String[]> tableModel = new ArrayList<>();
		for (CustomerRecord customerRecord : customerRecords) {
			String[] customer = new String[]{customerRecord.getCustomerCode(), customerRecord.getFirstName(), customerRecord.getLastName(), 
					customerRecord.getMobileNumber()+"", customerRecord.getEmail(), customerRecord.getGender(), customerRecord.getContactAddress(), 
					customerRecord.getCreated().toString(), customerRecord.getWebsite(), customerRecord.getDescription(), customerRecord.getUserLoginDetails()!=null?customerRecord.getUserLoginDetails().getUsername():""};
			tableModel.add(customer);
		}
		 String[][] tableModelArray = new String[tableModel.size()][];
		 tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
	
	public static String[][] getTableModel(CustomerRecord customerRecord) {
		 String[][] tableModelArray = new String[][]{{"Customer Code" , customerRecord.getCustomerCode()}, 
				 {"Name", customerRecord.getFirstName() +" "+ customerRecord.getLastName()},
				 {"MobileNumber", customerRecord.getMobileNumber()+""} , 
				 {"Gender", customerRecord.getGender()},
				 {"Email",  customerRecord.getEmail()},
				 {"Contact Address",  customerRecord.getContactAddress()},
				 {"Username",  customerRecord.getUserLoginDetails().getUsername()}
				 };
		return tableModelArray;
	}
}
