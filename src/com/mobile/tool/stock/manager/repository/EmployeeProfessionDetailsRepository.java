package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.EmployeeProfessionalDetails;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;

public class EmployeeProfessionDetailsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(emp_prof_id) FROM EMPLOYEES_PROFESSIONAL_DETAILS";
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

	public static void addEmployeeProfessionalDetails(
			EmployeeProfessionalDetails employeeProfessionalDetails) {
		String query = "INSERT INTO EMPLOYEES_PROFESSIONAL_DETAILS VALUES ("
				+ (getCurrentIdCursor() + 1) + ",'"
				+ employeeProfessionalDetails.getEmpolyee().getEmployeeCode()
				+ "','" + employeeProfessionalDetails.getCompanyName() + "','"
				+ employeeProfessionalDetails.getJobTitle() + "','"
				+ employeeProfessionalDetails.getStartDate() + "','"
				+ employeeProfessionalDetails.getEndDate() + "','"
				+ employeeProfessionalDetails.getResponsobilities() + "')";
		jdbcTemplate.executeUpdate(query);
	}

	public static List<EmployeeProfessionalDetails> getEmployeeProfessionalDetailsByQuery(
			String query) {
		List<EmployeeProfessionalDetails> empProfDetailsList = new ArrayList<EmployeeProfessionalDetails>();
		ResultSet employeeProfesionalDetailsInDb = null;
		try {
			employeeProfesionalDetailsInDb = jdbcTemplate.executeQuery(query);
			while (employeeProfesionalDetailsInDb.next()) {
				String employeeCode = employeeProfesionalDetailsInDb
						.getString("employee_code");
				EmployeeRecord employeeRecord = EmployeeRecordsRepository
						.getEmployeeRecordByEmployeeCode(employeeCode);
				empProfDetailsList.add(new EmployeeProfessionalDetails(
						employeeProfesionalDetailsInDb
								.getInt("emp_prof_id"),
						employeeRecord, employeeProfesionalDetailsInDb
								.getString("company_name"),
						employeeProfesionalDetailsInDb
								.getString("designation"),
						employeeProfesionalDetailsInDb.getDate("start_date"),
						employeeProfesionalDetailsInDb.getDate("end_date"),
						employeeProfesionalDetailsInDb
								.getString("responsibilities")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (employeeProfesionalDetailsInDb != null)
				try {
					employeeProfesionalDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return empProfDetailsList;
	}

	public static List<EmployeeProfessionalDetails> getAllEmployeeProfessionalDetails() {
		String query = "SELECT * FROM EMPLOYEES_PROFESSIONAL_DETAILS ORDER BY emp_prof_id desc";
		return getEmployeeProfessionalDetailsByQuery(query);
	}

	public static List<EmployeeProfessionalDetails> getEmployeeProfessionalDetailsByEmployeeCode(
			String employeeCode) {
		String query = "SELECT * FROM EMPLOYEES_PROFESSIONAL_DETAILS WHERE employee_code ='"
				+ employeeCode + "'";
		return getEmployeeProfessionalDetailsByQuery(query);
	}

	public static void main(String[] args) {
		System.out.println(getAllEmployeeProfessionalDetails());
	}
}
