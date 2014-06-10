package com.mobile.tool.stock.manager.entity;

public class VendorCategory {

	private int vendorCategoryId;
	private String vendorCategoryName;
	private String vendorCategoryDesc;
	
	public VendorCategory() {
	}
	
	public VendorCategory(int vendorCategoryId, String vendorCategoryName,
			String vendorCategoryDesc) {
		super();
		this.vendorCategoryId = vendorCategoryId;
		this.vendorCategoryName = vendorCategoryName;
		this.vendorCategoryDesc = vendorCategoryDesc;
	}


	public int getVendorCategoryId() {
		return vendorCategoryId;
	}
	public void setVendorCategoryId(int vendorCategoryId) {
		this.vendorCategoryId = vendorCategoryId;
	}
	public String getVendorCategoryName() {
		return vendorCategoryName;
	}
	public void setVendorCategoryName(String vendorCategoryName) {
		this.vendorCategoryName = vendorCategoryName;
	}
	public String getVendorCategoryDesc() {
		return vendorCategoryDesc;
	}
	public void setVendorCategoryDesc(String vendorCategoryDesc) {
		this.vendorCategoryDesc = vendorCategoryDesc;
	}
	@Override
	public String toString() {
		return "VendorCategory [vendorCategoryId=" + vendorCategoryId
				+ ", vendorCategoryName=" + vendorCategoryName
				+ ", vendorCategoryDesc=" + vendorCategoryDesc + "]";
	}

}
