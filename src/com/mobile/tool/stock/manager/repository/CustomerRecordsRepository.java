package com.mobile.tool.stock.manager.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;

public class CustomerRecordsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getMySQLJdbcTemplate();

	public static void addCustomerRecord(CustomerRecord customerRecord) {
		String query = "INSERT INTO CUSTOMER_RECORD VALUES ('"
				+ (customerRecord.getFirstName().toUpperCase().substring(0, 3)
						+ customerRecord.getLastName().toUpperCase()
								.substring(0, 3) + getRandomNo())
				+ "','"
				+ customerRecord.getFirstName()
				+ "','"
				+ customerRecord.getLastName()
				+ "',"
				+ customerRecord.getMobileNumber()
				+ ",'"
				+ customerRecord.getEmail()
				+ "','"
				+ customerRecord.getGender()
				+ "','"
				+ customerRecord.getContactAddress()
				+ "','"
				+ new Date(System.currentTimeMillis())
				+ "','"
				+ customerRecord.getWebsite()
				+ "','"
				+ customerRecord.getDescription()
				+ "',"
				+ (customerRecord.getUserLoginDetails() != null ? "'"
						+ customerRecord.getUserLoginDetails().getUsername()
						+ "'" : "NULL") + ")";
		jdbcTemplate.executeUpdate(query);
	}

	private static int getRandomNo() {
		return (int) (Math.random() * 1000);
	}

	public static void main(String[] args) {
		System.out.println(getRandomNo());
	}

	public static void removeCustomerRecord(CustomerRecord customerRecord) {
		if (customerRecord.getCustomerCode() == null)
			throw new IllegalArgumentException("customer code can not be null");

		String query = "DELETE CUSTOMER_RECORD WHERE id="
				+ customerRecord.getCustomerCode();
		jdbcTemplate.executeUpdate(query);
	}

	public static List<CustomerRecord> getCustomerRecordByQuery(String query) {
		List<CustomerRecord> customerRecord = new ArrayList<CustomerRecord>();
		ResultSet customerRecordInDb = null;
		try {
			customerRecordInDb = jdbcTemplate.executeQuery(query);
			while (customerRecordInDb.next()) {
				String username = customerRecordInDb.getString("username");
				UserLoginDetails userLoginDetails = UserLoginDetailsRepository
						.getUserLoginDetailsByUsername(username);
				customerRecord.add(new CustomerRecord(customerRecordInDb
						.getString("customer_code"), customerRecordInDb
						.getString("first_name"), customerRecordInDb
						.getString("last_name"), customerRecordInDb
						.getInt("mobile_number"), customerRecordInDb
						.getString("email"), customerRecordInDb
						.getString("gender"), customerRecordInDb
						.getString("contact_address"), customerRecordInDb
						.getDate("created"), customerRecordInDb
						.getString("website"), customerRecordInDb
						.getString("description"), userLoginDetails));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (customerRecordInDb != null)
				try {
					customerRecordInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return customerRecord;
	}

	public static List<CustomerRecord> getAllCustomerRecords() {
		String query = "SELECT * FROM CUSTOMER_RECORD ORDER BY customer_code desc";
		return getCustomerRecordByQuery(query);
	}

	public static CustomerRecord getCustomerRecordByCustomerCode(
			String customerCode) {
		String query = "SELECT * FROM CUSTOMER_RECORD WHERE customer_code ='"
				+ customerCode + "'";
		return getCustomerRecordByQuery(query).get(0);
	}

	public static CustomerRecord getCustomerRecordByByUsername(String username) {
		String query = "SELECT * FROM CUSTOMER_RECORD WHERE username ='"
				+ username + "'";
		return getCustomerRecordByQuery(query).get(0);
	}

	public static void updateCustomerUserLoginDetails(
			CustomerRecord customerRecord) {
		if (customerRecord != null && customerRecord.getCustomerCode() != null) {
			String query = "UPDATE CUSTOMER_RECORD SET username = "
					+ (customerRecord.getUserLoginDetails() != null ? "'"
							+ customerRecord.getUserLoginDetails()
									.getUsername() + "'" : "NULL")
					+ " WHERE customer_code='"
					+ customerRecord.getCustomerCode() + "'";
			jdbcTemplate.executeUpdate(query);
		}

	}
}
