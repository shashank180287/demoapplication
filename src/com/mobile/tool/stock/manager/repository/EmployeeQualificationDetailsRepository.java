package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.EmployeeQualificationDetails;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;

public class EmployeeQualificationDetailsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(emp_qualification_id) FROM EMPLOYEES_QUALIFICATION_DETAILS";
		ResultSet maxId = null;
		try {
			maxId = jdbcTemplate.executeQuery(query);
			while (maxId.next())
				return maxId.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (maxId != null)
				try {
					maxId.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return 0;
	}

	public static void addEmployeeQualificationDetails(
			EmployeeQualificationDetails employeeQualificationDetails) {
		String query = "INSERT INTO EMPLOYEES_QUALIFICATION_DETAILS VALUES ("
				+ (getCurrentIdCursor() + 1) + ",'"
				+ employeeQualificationDetails.getEmpolyee().getEmployeeCode()
				+ "','" + employeeQualificationDetails.getDegreeName() + "','"
				+ employeeQualificationDetails.getInstitutionName() + "','"
				+ employeeQualificationDetails.getStartDate() + "','"
				+ employeeQualificationDetails.getEndDate() + "',"
				+ employeeQualificationDetails.getPercentage() + ")";
		jdbcTemplate.executeUpdate(query);
	}

	public static List<EmployeeQualificationDetails> getEmployeeQualificationDetailsByQuery(
			String query) {
		List<EmployeeQualificationDetails> empQualDetailsList = new ArrayList<EmployeeQualificationDetails>();
		ResultSet employeeQualificationDetailsInDb = null;
		try {
			employeeQualificationDetailsInDb = jdbcTemplate.executeQuery(query);
			while (employeeQualificationDetailsInDb.next()) {
				String employeeCode = employeeQualificationDetailsInDb
						.getString("employee_code");
				EmployeeRecord employeeRecord = EmployeeRecordsRepository
						.getEmployeeRecordByEmployeeCode(employeeCode);
				empQualDetailsList.add(new EmployeeQualificationDetails(
						employeeQualificationDetailsInDb
								.getInt("emp_qualification_id"),
						employeeRecord, employeeQualificationDetailsInDb
								.getString("degree_name"),
						employeeQualificationDetailsInDb
								.getString("institution_name"),
						employeeQualificationDetailsInDb.getDate("start_date"),
						employeeQualificationDetailsInDb.getDate("end_date"),
						employeeQualificationDetailsInDb
								.getDouble("percentage")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (employeeQualificationDetailsInDb != null)
				try {
					employeeQualificationDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return empQualDetailsList;
	}

	public static List<EmployeeQualificationDetails> getAllEmployeeQualificationDetails() {
		String query = "SELECT * FROM EMPLOYEES_QUALIFICATION_DETAILS ORDER BY emp_qualification_id desc";
		return getEmployeeQualificationDetailsByQuery(query);
	}

	public static List<EmployeeQualificationDetails> getEmployeeQualificationDetailsByEmployeeCode(
			String employeeCode) {
		String query = "SELECT * FROM EMPLOYEES_QUALIFICATION_DETAILS WHERE employee_code ='"
				+ employeeCode + "'";
		return getEmployeeQualificationDetailsByQuery(query);
	}

	public static void main(String[] args) {
		System.out.println(getAllEmployeeQualificationDetails());
	}
}
