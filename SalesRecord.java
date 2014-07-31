package com.mobile.tool.stock.manager.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SalesRecord {

	private String salesCode;
	private ProductRecord product;
	private int noOfItems;
	private String salesTitle;
	private String salesDesc;
	private float salesAmount;
	private float amountPaid;
	private float balance;
	private TransectionMode mode;
	private CustomerRecord customer;
	private EmployeeRecord employee;
	private String comments;
	private Date created;
	public static String[] tableColumnNames = new String[]{"Sale Code","Product Code","Total Items","Sales Amount",
		"Amount Paid", "Balance", "Mode", "Customer Name","Employee Name","comment","Created On"};
	
	public SalesRecord() {
	}

	public SalesRecord(String salesCode, ProductRecord product, int noOfItems,
			String salesTitle, String salesDesc, float salesAmount,
			float amountPaid, float balance, TransectionMode mode, CustomerRecord customer,
			EmployeeRecord employee, String comments, Date created) {
		super();
		this.salesCode = salesCode;
		this.product = product;
		this.noOfItems = noOfItems;
		this.salesTitle = salesTitle;
		this.salesDesc = salesDesc;
		this.salesAmount = salesAmount;
		this.amountPaid = amountPaid;
		this.balance = balance;
		this.mode = mode;
		this.customer = customer;
		this.employee = employee;
		this.comments = comments;
		this.created = created;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public ProductRecord getProduct() {
		return product;
	}

	public void setProduct(ProductRecord product) {
		this.product = product;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String getSalesTitle() {
		return salesTitle;
	}

	public void setSalesTitle(String salesTitle) {
		this.salesTitle = salesTitle;
	}

	public String getSalesDesc() {
		return salesDesc;
	}

	public void setSalesDesc(String salesDesc) {
		this.salesDesc = salesDesc;
	}

	public float getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(float salesAmount) {
		this.salesAmount = salesAmount;
	}

	public TransectionMode getMode() {
		return mode;
	}

	public void setMode(TransectionMode mode) {
		this.mode = mode;
	}

	public CustomerRecord getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerRecord customer) {
		this.customer = customer;
	}

	public EmployeeRecord getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeRecord employee) {
		this.employee = employee;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "SalesRecord [salesCode=" + salesCode + ", product=" + product
				+ ", noOfItems=" + noOfItems + ", salesTitle=" + salesTitle
				+ ", salesDesc=" + salesDesc + ", salesAmount=" + salesAmount
				+ ", amountPaid=" + amountPaid + ", balance=" + balance 
				+ ", mode=" + mode + ", customer=" + customer + ", employee=" + employee
				+ ", comments=" + comments + ", created=" + created + "]";
	}
	
	public static String[][] getTableModel(List<SalesRecord> salesRecords) {
		List<String[]> tableModel = new ArrayList<String[]>();
		for (SalesRecord saleRecord : salesRecords) {
			String[] customer = new String[]{saleRecord.getSalesCode(), saleRecord.getProduct().getProductCode(), saleRecord.getNoOfItems()+"", 
					saleRecord.getSalesAmount()+"", saleRecord.getAmountPaid()+"", saleRecord.getBalance()+"", saleRecord.getMode().getChannelName(), saleRecord.getCustomer().getFirstName(), 
					saleRecord.getEmployee().getFirstname(), saleRecord.getComments(), saleRecord.getCreated().toString()};
			tableModel.add(customer);
		}
		 String[][] tableModelArray = new String[tableModel.size()][];
		 tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
	
}
