package com.mobile.tool.stock.manager.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductCatagory {

	public static String[] tableColumnNames = { "Id", "Product Name",
			"Product Description" };
	private int categoryId;
	private String categoryName;
	private String categoryDescription;

	public ProductCatagory() {
	}

	public ProductCatagory(int categoryId, String categoryName,
			String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		return "ProductCatagory [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryDescription=" + categoryDescription
				+ "]";
	}

	public static String[][] getTableModel(
			List<ProductCatagory> allProductCategories) {
		List<String[]> tableModel = new ArrayList<String[]>();
		for (ProductCatagory productCatagory : allProductCategories) {
			String[] productCategory = new String[] {
					productCatagory.getCategoryId() + "",
					productCatagory.getCategoryName(),
					productCatagory.getCategoryDescription() };
			tableModel.add(productCategory);
		}
		String[][] tableModelArray = new String[tableModel.size()][];
		tableModel.toArray(tableModelArray);
		return tableModelArray;
	}

}
