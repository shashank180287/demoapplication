package com.mobile.tool.stock.manager.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OwerDetails {

	private long id;
	private String saleCode;
	private String customerName;
	private float totalAmount;
	private float amountPaid;
	private float amountOwe;
	private Date purchaseDate;
	public static String[] tableColumnNames = new String[]{"Ower Id", "Sale Code", "Customer Name","Amount","Amount Paid","Amount Owing",
	"Purchase Date"};
	
	public OwerDetails(long id, String saleCode, String customerName,
			float totalAmount, float amountPaid, float amountOwe,
			Date purchaseDate) {
		super();
		this.id = id;
		this.saleCode = saleCode;
		this.customerName = customerName;
		this.totalAmount = totalAmount;
		this.amountPaid = amountPaid;
		this.amountOwe = amountOwe;
		this.purchaseDate = purchaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public float getAmountOwe() {
		return amountOwe;
	}

	public void setAmountOwe(float amountOwe) {
		this.amountOwe = amountOwe;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "OwerDetails [id=" + id + ", saleCode=" + saleCode
				+ ", customerName=" + customerName + ", totalAmount="
				+ totalAmount + ", amountPaid=" + amountPaid + ", amountOwe="
				+ amountOwe + ", purchaseDate=" + purchaseDate + "]";
	}
	
	public static String[][] getTableModel(List<OwerDetails> owerDetailsList) {
		List<String[]> tableModel = new ArrayList<String[]>();
		for (OwerDetails owerDetails : owerDetailsList) {
			String[] ower = new String[]{owerDetails.getId()+"",
					owerDetails.getSaleCode(), owerDetails.getCustomerName(), owerDetails.getTotalAmount()+"", 
					owerDetails.getAmountPaid()+"", owerDetails.getAmountOwe()+"", owerDetails.getPurchaseDate().toString()};
			tableModel.add(ower);
		}
		String[][] tableModelArray = new String[tableModel.size()][];
		tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
}
