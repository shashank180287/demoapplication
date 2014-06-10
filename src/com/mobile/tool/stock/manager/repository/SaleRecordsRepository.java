package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.entity.TransectionMode;

public class SaleRecordsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getMySQLJdbcTemplate();

	public static void addSalesRecord(SalesRecord salesRecord) {
		String query = "INSERT INTO SALES_RECORDS VALUES ('"
				+ (salesRecord.getProduct().getName().toUpperCase()
						.substring(0, 3) + System.currentTimeMillis()) + "','"
				+ salesRecord.getProduct().getProductCode() + "',"
				+ salesRecord.getNoOfItems() + ",'"
				+ salesRecord.getSalesTitle() + "','"
				+ salesRecord.getSalesDesc() + "',"
				+ salesRecord.getSalesAmount() + ","
				+ salesRecord.getConfirmAmount() + ","
				+ salesRecord.getMode().getChannelId() + ",'"
				+ salesRecord.getCustomer().getCustomerCode() + "','"
				+ salesRecord.getEmployee().getEmployeeCode() + "','"
				+ salesRecord.getComments() + "','" 
				+ salesRecord.getCreated()
				+ "')";
		jdbcTemplate.executeUpdate(query);
	}

	public static List<SalesRecord> getSalesRecordByQuery(String query) {
		List<SalesRecord> salesRecord = new ArrayList<SalesRecord>();
		ResultSet salesRecordInDb = null;
		try {
			salesRecordInDb = jdbcTemplate.executeQuery(query);
			if (salesRecordInDb != null) {
				while (salesRecordInDb.next()) {
					String productCode = salesRecordInDb
							.getString("product_code");
					ProductRecord product = ProductRecordRepository
							.getProductRecordByCode(productCode);
					int channelId = salesRecordInDb.getInt("channel_id");
					TransectionMode mode = TransectionModeRepository
							.getTransectionModeByModeId(channelId);
					String customerCode = salesRecordInDb
							.getString("customer_code");
					CustomerRecord cusotmer = CustomerRecordsRepository
							.getCustomerRecordByCustomerCode(customerCode);
					String employeeCode = salesRecordInDb
							.getString("employee_code");
					EmployeeRecord employee = EmployeeRecordsRepository
							.getEmployeeRecordByEmployeeCode(employeeCode);
					salesRecord.add(new SalesRecord(salesRecordInDb
							.getString("sales_code"), product, salesRecordInDb
							.getInt("no_of_items"), salesRecordInDb
							.getString("sales_title"), salesRecordInDb
							.getString("sales_desc"), salesRecordInDb
							.getFloat("sales_amount"), salesRecordInDb
							.getFloat("confirm_amount"), mode, cusotmer,
							employee, salesRecordInDb.getString("comments"),
							salesRecordInDb.getDate("created")));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (salesRecordInDb != null)
				try {
					salesRecordInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return salesRecord;
	}

	public static List<SalesRecord> getSaleRecordForEmployee(String employeeCode) {
		return getSalesRecordByQuery("SELECT * FROM SALES_RECORDS WHERE employee_code='"
				+ employeeCode + "' ORDER BY created desc");
	}
	
	public static List<SalesRecord> getSaleRecordForCustomer(String customerCode) {
		return getSalesRecordByQuery("SELECT * FROM SALES_RECORDS WHERE customer_code='"
				+ customerCode + "' ORDER BY created desc");
	}
}
