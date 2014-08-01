package com.mobile.tool.stock.manager.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductSupplyDetails {

	private long id;
	private ProductSupply productSupply;
	private ProductRecord product;
	private int totalItems;
	private int totalSupplied;
	private int currentStock;
	private int currentItemCount;
	public static String[] tableColumnNames = new String[]{"Supply Code", "Product", "Total Items", "Current Stock", "Current Items"};
	
	public ProductSupplyDetails() {

	}
	
	public ProductSupplyDetails(long id, ProductSupply productSupply,
			ProductRecord product, int totalItems, int totalSupplied,
			int currentStock, int currentItemCount) {
		super();
		this.id = id;
		this.productSupply = productSupply;
		this.product = product;
		this.totalItems = totalItems;
		this.totalSupplied = totalSupplied;
		this.currentStock = currentStock;
		this.currentItemCount = currentItemCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductSupply getProductSupply() {
		return productSupply;
	}

	public void setProductSupply(ProductSupply productSupply) {
		this.productSupply = productSupply;
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

	public static String[] getTableColumnNames() {
		return tableColumnNames;
	}

	public static void setTableColumnNames(String[] tableColumnNames) {
		ProductSupplyDetails.tableColumnNames = tableColumnNames;
	}

	@Override
	public String toString() {
		return "ProductSupplyDetails [id=" + id
				+ ", productSupply=" + productSupply + ", product=" + product
				+ ", totalItems=" + totalItems + ", totalSupplied="
				+ totalSupplied + ", currentStock=" + currentStock
				+ ", currentItemCount=" + currentItemCount + "]";
	}

	public static String[][] getTableModel(List<ProductSupplyDetails> productSupplyDetails) {
		List<String[]> tableModel = new ArrayList<>();
		for (ProductSupplyDetails productSupplyDetail : productSupplyDetails) {
			String[] supply = new String[]{productSupplyDetail.getProductSupply().getSupplyCode(), productSupplyDetail.getProduct().getName(), 
					productSupplyDetail.getTotalItems()+"", productSupplyDetail.getCurrentStock()+"", productSupplyDetail.getCurrentItemCount()+""};
			tableModel.add(supply);
		}
		String[][] tableModelArray = new String[tableModel.size()][];
		tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
}
