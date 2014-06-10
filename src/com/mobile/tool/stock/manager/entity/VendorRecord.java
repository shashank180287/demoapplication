package com.mobile.tool.stock.manager.entity;

public class VendorRecord {

	private String vendorCode;
	private VendorCategory vendorCategory;
	private String name;
	private String title;
	private String description;
	private long mobilenumber;
	private String email;
	private String contactAddress;
	private String permanentAddress;
	private	String website;
	private UserLoginDetails userLoginDetails;
	
	public VendorRecord() {
	}

	public VendorRecord(String vendorCode, VendorCategory vendorCategory,
			String name, String title, String description, int mobilenumber,
			String email, String contactAddress, String permanentAddress,
			String website, UserLoginDetails userLoginDetails) {
		super();
		this.vendorCode = vendorCode;
		this.vendorCategory = vendorCategory;
		this.name = name;
		this.title = title;
		this.description = description;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.contactAddress = contactAddress;
		this.permanentAddress = permanentAddress;
		this.website = website;
		this.userLoginDetails = userLoginDetails;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public VendorCategory getVendorCategory() {
		return vendorCategory;
	}

	public void setVendorCategory(VendorCategory vendorCategory) {
		this.vendorCategory = vendorCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public UserLoginDetails getUserLoginDetails() {
		return userLoginDetails;
	}

	public void setUserLoginDetails(UserLoginDetails userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}

	@Override
	public String toString() {
		return "VendorRecord [vendorCode=" + vendorCode + ", vendorCategory="
				+ vendorCategory + ", name=" + name + ", title=" + title
				+ ", description=" + description + ", mobilenumber="
				+ mobilenumber + ", email=" + email + ", contactAddress="
				+ contactAddress + ", permanentAddress=" + permanentAddress
				+ ", website=" + website + ", userLoginDetails="
				+ userLoginDetails + "]";
	}

}
