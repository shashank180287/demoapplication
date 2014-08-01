package com.mobile.tool.stock.manager.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.entity.TransectionMode;

public class SaleRecordsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

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

	public static List<SalesRecord> getSaleRecordForToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
		return getSalesRecordByQuery("SELECT * FROM SALES_RECORDS WHERE created>'"+new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())+"'");
	}
	
	public static String[][] getCustomerSalesRecord() {
		List<String[]> salesRecord = new ArrayList<String[]>();
		ResultSet salesRecordInDb = null;
		try {
			salesRecordInDb = jdbcTemplate.executeQuery("select customer_code, sum(confirm_amount) from sales_records group by customer_code order by sum(confirm_amount) desc");
			if (salesRecordInDb != null) {
				while (salesRecordInDb.next()) {
					String customerCode = salesRecordInDb
							.getString("customer_code");
					CustomerRecord cusotmer = CustomerRecordsRepository
							.getCustomerRecordByCustomerCode(customerCode);
					salesRecord.add(new String[]{cusotmer.getFirstName()+" "+cusotmer.getLastName(), salesRecordInDb.getDouble("sum")+""});
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (salesRecordInDb != null)
				try {
					salesRecordInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		String[][] sales = new String[salesRecord.size()][];
		salesRecord.toArray(sales);
		return sales;
	}
	
	public static String[][] getProductSalesRecord() {
		List<String[]> salesRecord = new ArrayList<String[]>();
		ResultSet salesRecordInDb = null;
		try {
			salesRecordInDb = jdbcTemplate.executeQuery("select product_code, sum(confirm_amount) from sales_records group by product_code order by sum(confirm_amount) desc");
			if (salesRecordInDb != null) {
				while (salesRecordInDb.next()) {
					String productCode = salesRecordInDb
							.getString("product_code");
					ProductRecord product = ProductRecordRepository
							.getProductRecordByCode(productCode);
					salesRecord.add(new String[]{product.getName(), salesRecordInDb.getDouble("sum")+""});
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (salesRecordInDb != null)
				try {
					salesRecordInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		String[][] sales = new String[salesRecord.size()][];
		salesRecord.toArray(sales);
		return sales;
	}
	
	public static List<SalesRecord> getSaleRecordsForDatePeriod(Date fromDate, Date toDate) {
		return getSalesRecordByQuery("SELECT * FROM SALES_RECORDS WHERE created BETWEEN '"+new SimpleDateFormat("yyyy-MM-dd").format(fromDate)+"' AND '"+
					new SimpleDateFormat("yyyy-MM-dd").format(toDate)+"' ORDER BY created");
	}
	
	public static Map<String, Double> getProductEquity() {
		Map<String, Double> equities = new HashMap<>();
		ResultSet productSupplyInDb = null;
		try{
			productSupplyInDb = jdbcTemplate.executeQuery("select product_code, sum(confirm_amount) from sales_records group"+
					" by product_code");
			while(productSupplyInDb.next()){
				equities.put(productSupplyInDb.getString("product_code"), productSupplyInDb.getDouble("sum"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(productSupplyInDb!=null)
				try {
					productSupplyInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return equities;
	}

	public static List<SalesRecord> getSaleRecordForManagerSupervisee(
			String managerUsername) {
		List<SalesRecord> salesRecord = new ArrayList<SalesRecord>();
		List<EmployeeRecord> superviseeList = EmployeeRecordsRepository.getEmployeeRecordsByManagerUserName(managerUsername);
		for (EmployeeRecord employeeRecord : superviseeList) {
			salesRecord.addAll(getSaleRecordForEmployee(employeeRecord.getEmployeeCode()));
		}
		return salesRecord;
	}
}
