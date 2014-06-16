package com.mobile.tool.stock.manager.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductRecord {

	private String productCode;
	private ProductCatagory productcategory;
	private String name;
	private String desc;
	private double unitPrice;
	private double bulkPrice;
	private int orderCount;
	private VendorRecord vendor;
	private Date created;
	public static String[] tableColumnNames = new String[]{"Product Code", "Category", "Name", "Description", "Unit Price",
		"Bulk Price", "Order Count", "Vender", "Created"};
	
	public ProductRecord() {
	}

	public ProductRecord(String productCode, ProductCatagory productcategory,
			String name, String desc, double unitPrice, double bulkPrice,
			int orderCount, VendorRecord vendor, Date created) {
		super();
		this.productCode = productCode;
		this.productcategory = productcategory;
		this.name = name;
		this.desc = desc;
		this.unitPrice = unitPrice;
		this.bulkPrice = bulkPrice;
		this.orderCount = orderCount;
		this.vendor = vendor;
		this.created = created;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public ProductCatagory getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(ProductCatagory productcategory) {
		this.productcategory = productcategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getBulkPrice() {
		return bulkPrice;
	}

	public void setBulkPrice(double bulkPrice) {
		this.bulkPrice = bulkPrice;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public VendorRecord getVendor() {
		return vendor;
	}

	public void setVendor(VendorRecord vendor) {
		this.vendor = vendor;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "ProductRecord [productCode=" + productCode
				+ ", productcategory=" + productcategory + ", name=" + name
				+ ", desc=" + desc + ", unitPrice=" + unitPrice
				+ ", bulkPrice=" + bulkPrice + ", orderCount=" + orderCount
				+ ", vendor=" + vendor + ", created=" + created + "]";
	}

	public static String[][] getTableModel(List<ProductRecord> productRecords) {
		List<String[]> tableModel = new ArrayList<>();
		for (ProductRecord productRecord : productRecords) {
			String[] customer = new String[]{productRecord.getProductCode(), productRecord.getProductcategory().getCategoryName(), 
					productRecord.getName(), productRecord.getDesc(), productRecord.getUnitPrice()+"", productRecord.getBulkPrice()+"", 
					productRecord.getOrderCount()+"", productRecord.getVendor().getName(), productRecord.getCreated().toString()};
			tableModel.add(customer);
		}
		 String[][] tableModelArray = new String[tableModel.size()][];
		 tableModel.toArray(tableModelArray);
		return tableModelArray;
	}

}
