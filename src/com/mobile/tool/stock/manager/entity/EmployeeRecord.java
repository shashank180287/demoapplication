package com.mobile.tool.stock.manager.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRecord {

	private String employeeCode;
	private String firstname;
	private String lastname;
	private long mobilenumber;
	private String gender;
	private String stateOfOrigin;
	private String localGovt;
	private int age;
	private String maritalStatus;
	private String jobDescription;
	private EmployeeRecord manager;
	private String email;
	private Date employeed;
	private String jobTitle;
	private String contactAddress;
	private String religion;
	private String idProofDocName;
	private String idProofDocNo;
	private UserLoginDetails userLoginDetail;
	private String reference1;
	private String reference2;
	private String reference3;
	public static String[] tableColumnNames = new String[]{"Employee Code","First Name","Last Name","Mobile Number",
		"Email", "Gender", "Religion", "Contact Address", "Employeed", "Job Title", "Manager", "Username"};
	public static String[] attributeColumnNames = new String[]{"Attribute Name","Attribute Value"};
	
	public EmployeeRecord() {
	}

	public EmployeeRecord(String employeeCode, String firstname,
			String lastname, long mobilenumber, String gender,
			String stateOfOrigin, String localGovt, int age,
			String maritalStatus, String jobDescription,
			EmployeeRecord manager, String email, Date employeed,
			String jobTitle, String contactAddress,
			UserLoginDetails userLoginDetail, String reference1,
			String reference2, String reference3, String religion, String idProofDocName, String idProofDocNo) {
		super();
		this.employeeCode = employeeCode;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobilenumber = mobilenumber;
		this.gender = gender;
		this.stateOfOrigin = stateOfOrigin;
		this.localGovt = localGovt;
		this.age = age;
		this.maritalStatus = maritalStatus;
		this.jobDescription = jobDescription;
		this.manager = manager;
		this.email = email;
		this.employeed = employeed;
		this.jobTitle = jobTitle;
		this.contactAddress = contactAddress;
		this.userLoginDetail = userLoginDetail;
		this.reference1 = reference1;
		this.reference2 = reference2;
		this.reference3 = reference3;
		this.religion = religion;
		this.idProofDocName = idProofDocName;
		this.idProofDocNo = idProofDocNo;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStateOfOrigin() {
		return stateOfOrigin;
	}

	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
	}

	public String getLocalGovt() {
		return localGovt;
	}

	public void setLocalGovt(String localGovt) {
		this.localGovt = localGovt;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public EmployeeRecord getManager() {
		return manager;
	}

	public void setManager(EmployeeRecord manager) {
		this.manager = manager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEmployeed() {
		return employeed;
	}

	public void setEmployeed(Date employeed) {
		this.employeed = employeed;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public UserLoginDetails getUserLoginDetail() {
		return userLoginDetail;
	}

	public void setUserLoginDetail(UserLoginDetails userLoginDetail) {
		this.userLoginDetail = userLoginDetail;
	}

	public String getReference1() {
		return reference1;
	}

	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	public String getReference2() {
		return reference2;
	}

	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	public String getReference3() {
		return reference3;
	}

	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getIdProofDocName() {
		return idProofDocName;
	}

	public void setIdProofDocName(String idProofDocName) {
		this.idProofDocName = idProofDocName;
	}

	public String getIdProofDocNo() {
		return idProofDocNo;
	}

	public void setIdProofDocNo(String idProofDocNo) {
		this.idProofDocNo = idProofDocNo;
	}


	@Override
	public String toString() {
		return "EmployeeRecord [employeeCode=" + employeeCode + ", firstname="
				+ firstname + ", lastname=" + lastname + ", mobilenumber="
				+ mobilenumber + ", gender=" + gender + ", stateOfOrigin="
				+ stateOfOrigin + ", localGovt=" + localGovt + ", age=" + age
				+ ", maritalStatus=" + maritalStatus + ", jobDescription="
				+ jobDescription + ", manager=" + manager + ", email=" + email
				+ ", employeed=" + employeed + ", jobTitle=" + jobTitle
				+ ", contactAddress=" + contactAddress + ", religion="
				+ religion + ", idProofDocName=" + idProofDocName
				+ ", idProofDocNo=" + idProofDocNo + ", userLoginDetail="
				+ userLoginDetail + ", reference1=" + reference1
				+ ", reference2=" + reference2 + ", reference3=" + reference3
				+ "]";
	}

	public static String[][] getTableModel(List<EmployeeRecord> employeeRecords) {
		List<String[]> tableModel = new ArrayList<>();
		for (EmployeeRecord employeeRecord : employeeRecords) {
			String[] customer = new String[]{employeeRecord.getEmployeeCode(), employeeRecord.getFirstname(), employeeRecord.getLastname(), 
					employeeRecord.getMobilenumber()+"", employeeRecord.getEmail(), employeeRecord.getGender(), employeeRecord.getReligion(), employeeRecord.getContactAddress(), 
					employeeRecord.getEmployeed().toString(), employeeRecord.getJobTitle(), (employeeRecord.getManager()!=null?employeeRecord.getManager().getFirstname()+" "+
					employeeRecord.getManager().getLastname():""), employeeRecord.getUserLoginDetail()!=null?employeeRecord.getUserLoginDetail().getUsername():""};
			tableModel.add(customer);
		}
		 String[][] tableModelArray = new String[tableModel.size()][];
		 tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
	
	public static String[][] getTableModel(EmployeeRecord employeeRecordByUsername) {
		 String[][] tableModelArray = new String[][]{{"Employee Code" , employeeRecordByUsername.getEmployeeCode()}, 
				 {"Name", employeeRecordByUsername.getFirstname() +" "+ employeeRecordByUsername.getLastname()},
				 {"MobileNumber", employeeRecordByUsername.getMobilenumber()+""} , 
				 {"Gender", employeeRecordByUsername.getGender()},
				 {"stateOfOrigin", employeeRecordByUsername.getStateOfOrigin()},
				 {"Age",  employeeRecordByUsername.getAge()+""},
				 {"Marital Status",  employeeRecordByUsername.getMaritalStatus()},
				 {"Manager",  (employeeRecordByUsername.getManager()!=null)?employeeRecordByUsername.getManager().getFirstname()+" "+ employeeRecordByUsername.getManager().getLastname():""},
				 {"Email",  employeeRecordByUsername.getEmail()},
				 {"Contact Address",  employeeRecordByUsername.getContactAddress()},
				 {"Username",  employeeRecordByUsername.getUserLoginDetail().getUsername()}
				 };
		return tableModelArray;
	}
	
}
