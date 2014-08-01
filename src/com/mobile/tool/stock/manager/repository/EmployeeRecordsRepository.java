package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;

public class EmployeeRecordsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	public static String addNewEmployeeRecord(EmployeeRecord employeeRecord) {
		String employeeCode =getFirstThreeLetter(employeeRecord.getFirstname()
				.toUpperCase())
				+ getFirstThreeLetter(employeeRecord.getLastname()
						.toUpperCase()) + getRandomNo();
		String query = "INSERT INTO EMPLOYEES_RECORDS VALUES ('"
				+ (employeeCode)
				+ "','"
				+ employeeRecord.getFirstname()
				+ "','"
				+ employeeRecord.getLastname()
				+ "',"
				+ employeeRecord.getMobilenumber()
				+ ",'"
				+ employeeRecord.getGender()
				+ "','"
				+ employeeRecord.getStateOfOrigin()
				+ "','"
				+ employeeRecord.getLocalGovt()
				+ "',"
				+ employeeRecord.getAge()
				+ ",'"
				+ employeeRecord.getMaritalStatus()
				+ "','"
				+ employeeRecord.getJobDescription()
				+ "',"
				+ (employeeRecord.getManager()!=null?"'"+employeeRecord.getManager().getEmployeeCode()+"'":"NULL")
				+ ",'"
				+ employeeRecord.getEmail()
				+ "','"
				+ employeeRecord.getEmployeed()
				+ "','"
				+ employeeRecord.getJobTitle()
				+ "','"
				+ employeeRecord.getContactAddress()
				+ "','"
				+ employeeRecord.getReligion()
				+ "','"
				+ employeeRecord.getIdProofDocName()
				+ "','"
				+ employeeRecord.getIdProofDocNo()
				+ "',"
				+ (employeeRecord.getUserLoginDetail() != null ? "'"
						+ employeeRecord.getUserLoginDetail().getUsername()
						+ "'" : "NULL")
				+ ","
				+ (employeeRecord.getReference1() != null ? "'"
						+ employeeRecord.getReference1() + "'" : "NULL")
				+ ","
				+ (employeeRecord.getReference2() != null ? "'"
						+ employeeRecord.getReference2() + "'" : "NULL")
				+ ","
				+ (employeeRecord.getReference3() != null ? "'"
						+ employeeRecord.getReference3() + "'" : "NULL") + ")";
		jdbcTemplate.executeUpdate(query);
		return employeeCode;
	}

	public static List<EmployeeRecord> getEmployeeRecordByQuery(String query) {
		List<EmployeeRecord> customerRecord = new ArrayList<EmployeeRecord>();
		ResultSet employeeRecordInDb = null;
		try {
			employeeRecordInDb = jdbcTemplate.executeQuery(query);
			while (employeeRecordInDb.next()) {
				String username = employeeRecordInDb.getString("username");
				UserLoginDetails userLoginDetails = UserLoginDetailsRepository
						.getUserLoginDetailsByUsername(username);
				String managerCode = employeeRecordInDb
						.getString("manager_code");
				EmployeeRecord manager = managerCode != null ? getEmployeeRecordByEmployeeCode(managerCode)
						: null;
				customerRecord.add(new EmployeeRecord(employeeRecordInDb
						.getString("employee_code"), employeeRecordInDb
						.getString("first_name"), employeeRecordInDb
						.getString("last_name"), employeeRecordInDb
						.getLong("mobile_number"), employeeRecordInDb
						.getString("gender"), employeeRecordInDb
						.getString("state_of_origin"), employeeRecordInDb
						.getString("local_govt"), employeeRecordInDb
						.getInt("age"), employeeRecordInDb
						.getString("marital_status"), employeeRecordInDb
						.getString("job_description"), manager,
						employeeRecordInDb.getString("email"),
						employeeRecordInDb.getDate("employeed"),
						employeeRecordInDb.getString("job_title"),
						employeeRecordInDb.getString("contact_address"),
						userLoginDetails, employeeRecordInDb
								.getString("reference_1"), employeeRecordInDb
								.getString("reference_2"), employeeRecordInDb
								.getString("reference_3"), employeeRecordInDb
								.getString("religion"), employeeRecordInDb
								.getString("id_proof_doc_name"), employeeRecordInDb
								.getString("id_proof_doc_no")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (employeeRecordInDb != null)
				try {
					employeeRecordInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return customerRecord;
	}

	public static List<EmployeeRecord> getAllEmployeeRecords() {
		String query = "SELECT * FROM EMPLOYEES_RECORDS ORDER BY employee_code desc";
		return getEmployeeRecordByQuery(query);
	}

	public static EmployeeRecord getEmployeeRecordByEmployeeCode(
			String employeeCode) {
		String query = "SELECT * FROM EMPLOYEES_RECORDS WHERE employee_code ='"
				+ employeeCode + "'";
		return getEmployeeRecordByQuery(query).get(0);
	}

	public static EmployeeRecord getEmployeeRecordByUsername(String username) {
		String query = "SELECT * FROM EMPLOYEES_RECORDS WHERE username ='"
				+ username + "'";
		return getEmployeeRecordByQuery(query).get(0);
	}

	private static int getRandomNo() {
		return (int) (Math.random() * 1000);
	}

	private static String getFirstThreeLetter(String string) {
		return (string != null && string.length() > 2) ? string.substring(0, 2)
				: string;
	}

	public static void updateEmployeeUserLoginDetails(
			EmployeeRecord employeeRecord) {
		if (employeeRecord != null && employeeRecord.getEmployeeCode() != null) {
			String query = "UPDATE EMPLOYEES_RECORDS SET username = "
					+ (employeeRecord.getUserLoginDetail() != null ? "'"
							+ employeeRecord.getUserLoginDetail()
									.getUsername() + "'" : "NULL")
					+ " WHERE employee_code='"
					+ employeeRecord.getEmployeeCode() + "'";
			jdbcTemplate.executeUpdate(query);
		}
	}
	
	public static List<EmployeeRecord> getEmployeeRecordsByManagerCode(String managerCode) {
		String query = "SELECT * FROM EMPLOYEES_RECORDS WHERE manager_code ='" + managerCode + "'";
		return getEmployeeRecordByQuery(query);
	}

	public static List<EmployeeRecord> getEmployeeRecordsByManagerUserName(String managerUsername) {
		EmployeeRecord manager = getEmployeeRecordByUsername(managerUsername);
		if(manager!=null){
			return getEmployeeRecordsByManagerCode(manager.getEmployeeCode());
		}
		return new ArrayList<EmployeeRecord>();
	}

	public static boolean isUserManager(String username) {
		List<EmployeeRecord> supervisee = getEmployeeRecordsByManagerUserName(username);
		if(supervisee!=null && supervisee.size()>0)
			return true;
		return false;
	}
}
