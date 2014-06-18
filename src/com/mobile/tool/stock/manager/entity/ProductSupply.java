package com.mobile.tool.stock.manager.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductSupply {

	private String supplyCode;
	private ProductRecord product;
	private int totalItems;
	private int totalSupplied;
	private int currentStock;
	private int currentItemCount;
	public static String[] tableColumnNames = new String[]{"Supply Code", "Product", "Total Items", "Current Stock", "Current Items"};
	
	public ProductSupply() {
	}

	public ProductSupply(String supplyCode, ProductRecord product,
			int totalItems, int totalSupplied, int currentStock,
			int currentItemCount) {
		super();
		this.supplyCode = supplyCode;
		this.product = product;
		this.totalItems = totalItems;
		this.totalSupplied = totalSupplied;
		this.currentStock = currentStock;
		this.currentItemCount = currentItemCount;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public ProductRecord getProduct() {
		return product;
	}

	public void setProduct(ProductRecord product) {
		this.product = product;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalSupplied() {
		return totalSupplied;
	}

	public void setTotalSupplied(int totalSupplied) {
		this.totalSupplied = totalSupplied;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public int getCurrentItemCount() {
		return currentItemCount;
	}

	public void setCurrentItemCount(int currentItemCount) {
		this.currentItemCount = currentItemCount;
	}

	@Override
	public String toString() {
		return "ProductSupply [supplyCode=" + supplyCode + ", product="
				+ product + ", totalItems=" + totalItems + ", totalSupplied="
				+ totalSupplied + ", currentStock=" + currentStock
				+ ", currentItemCount=" + currentItemCount + "]";
	}
	
	public static String[][] getTableModel(List<ProductSupply> productSupplies) {
		List<String[]> tableModel = new ArrayList<>();
		for (ProductSupply productSupply : productSupplies) {
			String[] supply = new String[]{productSupply.getSupplyCode(), productSupply.getProduct().getName(), 
					productSupply.getTotalItems()+"", productSupply.getCurrentStock()+"", productSupply.getCurrentItemCount()+""};
			tableModel.add(supply);
		}
		String[][] tableModelArray = new String[tableModel.size()][];
		tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
}
