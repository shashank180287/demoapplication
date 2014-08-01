package com.mobile.tool.stock.manager.entity;

import java.util.ArrayList;
import java.util.List;

public class VendorCategory {

	public static String[] tableColumnNames={"Id", "Category Name", "Category Description"};
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

	public static String[][] getTableModel(
			List<VendorCategory> allVendorCategories) {
		List<String[]> tableModel = new ArrayList<String[]>();
		for (VendorCategory vendorCategory : allVendorCategories) {
			String[] userLogin = new String[]{vendorCategory.getVendorCategoryId()+"", vendorCategory.getVendorCategoryName(), vendorCategory.getVendorCategoryDesc()};
			tableModel.add(userLogin);
		}
		 String[][] tableModelArray = new String[tableModel.size()][];
		 tableModel.toArray(tableModelArray);
		return tableModelArray;
	}

}
