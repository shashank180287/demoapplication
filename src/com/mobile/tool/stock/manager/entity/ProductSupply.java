package com.mobile.tool.stock.manager.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductSupply {

	private String supplyCode;
	private Date supplyDate;
	private String supplyDetails;
	private String supplier;
	private List<ProductSupplyDetails> productSupplyDetails;
	public static String[] tableColumnNames = new String[]{"Supply Code", "Supply Date", "Description", "Supplier", "Item Counts"};
	public static String[] currentStockColumnNames = new String[]{"Product Name", "Current Stock", "Status"};
	private static final int LOW_STOCK_COUNT = 10;
	
	public ProductSupply() {
	
	}

	public ProductSupply(String supplyCode, Date supplyDate,
			String supplyDetails, String supplier) {
		super();
		this.supplyCode = supplyCode;
		this.supplyDate = supplyDate;
		this.supplyDetails = supplyDetails;
		this.supplier = supplier;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public Date getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(Date supplyDate) {
		this.supplyDate = supplyDate;
	}

	public String getSupplyDetails() {
		return supplyDetails;
	}

	public void setSupplyDetails(String supplyDetails) {
		this.supplyDetails = supplyDetails;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public List<ProductSupplyDetails> getProductSupplyDetails() {
		return productSupplyDetails;
	}

	public ProductSupply setProductSupplyDetails(
			List<ProductSupplyDetails> productSupplyDetails) {
		this.productSupplyDetails = productSupplyDetails;
		return this;
	}

	@Override
	public String toString() {
		return "ProductSupply [supplyCode=" + supplyCode + ", supplyDate="
				+ supplyDate + ", supplyDetails=" + supplyDetails
				+ ", supplier=" + supplier + ", productSupplyDetails="
				+ productSupplyDetails + "]";
	}

	public static String[][] getTableModel(List<ProductSupply> productSupplies) {
		List<String[]> tableModel = new ArrayList<>();
		for (ProductSupply productSupply : productSupplies) {
			String[] supply = new String[]{productSupply.getSupplyCode(), productSupply.getSupplyDate().toString(), 
					productSupply.getSupplyDetails(), productSupply.getSupplier(), (productSupply.getProductSupplyDetails()!=null?productSupply.getProductSupplyDetails().size()+"":0+"")};
			tableModel.add(supply);
		}
		String[][] tableModelArray = new String[tableModel.size()][];
		tableModel.toArray(tableModelArray);
		return tableModelArray;
	}

	public static String[][] getTableModelForCurrentStock(
			Map<String, Integer> currentStockByProduct) {
		List<String[]> tableModel = new ArrayList<>();
		for (String productName : currentStockByProduct.keySet()) {
			Integer currentStock = currentStockByProduct.get(productName);
			String stockStatus = "Proper Stock";
			if(currentStock==null || currentStock==0){
				stockStatus = "Out of Stock";
			}else if(currentStock<LOW_STOCK_COUNT){
				stockStatus = "Low Stock";
			}
			String[] supply = new String[]{productName, currentStock+"",  stockStatus};
			tableModel.add(supply);
		}
		String[][] tableModelArray = new String[tableModel.size()][];
		tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
}
