package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.EmployeePayrollDetails;
import com.mobile.tool.stock.manager.entity.EmployeeRecord;

public class EmployeePayrollDetailsRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate
			.getDerbyJdbcTemplate();

	private static int getCurrentIdCursor() {
		String query = "SELECT MAX(emp_payroll_id) FROM EMPLOYEES_PAYROLL_DETAIL";
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

	public static void addEmployeePayrollDetails(
			EmployeePayrollDetails employeePayrollDetails) {
		String query = "INSERT INTO EMPLOYEES_PAYROLL_DETAIL VALUES ("
				+ (getCurrentIdCursor() + 1) + ",'"
				+ employeePayrollDetails.getEmployeeRecord().getEmployeeCode() + "','" 
				+ employeePayrollDetails.getDepartment() + "',"
				+ employeePayrollDetails.getSickLeaves() + ","
				+ employeePayrollDetails.getCasualLeaves() + ","
				+ employeePayrollDetails.getAnnualLeaves() + ","
				+ employeePayrollDetails.getStudyLeaves() + ","
				+ employeePayrollDetails.getOther() + ","
				+ employeePayrollDetails.getDaysOfLeaves() + ",'"
				+ employeePayrollDetails.getLeaveStartDate() + "','"
				+ employeePayrollDetails.getLeaveEndDate() + "','"
				+ employeePayrollDetails.getLoanName() + "',"
				+ employeePayrollDetails.getIntertestRate() + ",'"
				+ employeePayrollDetails.getGradeLevel() + "',"
				+ employeePayrollDetails.getAllowances() + ","
				+ employeePayrollDetails.getInitialSalary() + ","
				+ employeePayrollDetails.getUpgradeSalary() + ",'"
				+ employeePayrollDetails.getEmploymentType() + "')";
		jdbcTemplate.executeUpdate(query);
	}

	public static List<EmployeePayrollDetails> getEmployeePayrollDetailsByQuery(
			String query) {
		List<EmployeePayrollDetails> employeePayrollDetailsList = new ArrayList<EmployeePayrollDetails>();
		ResultSet employeePayrollDetailsInDb = null;
		try {
			employeePayrollDetailsInDb = jdbcTemplate.executeQuery(query);
			while (employeePayrollDetailsInDb.next()) {
				String employeeCode = employeePayrollDetailsInDb.getString("employee_code");
				EmployeeRecord employeeRecord = EmployeeRecordsRepository.getEmployeeRecordByEmployeeCode(employeeCode);
				employeePayrollDetailsList.add(new EmployeePayrollDetails(
						employeePayrollDetailsInDb.getInt("emp_payroll_id"),
						employeeRecord,
						employeePayrollDetailsInDb.getString("department"),
						employeePayrollDetailsInDb.getDouble("sick_leaves"),
						employeePayrollDetailsInDb.getDouble("casual_leaves"),
						employeePayrollDetailsInDb.getDouble("annual_leaves"),
						employeePayrollDetailsInDb.getDouble("study_leaves"),
						employeePayrollDetailsInDb.getDouble("other"),
						employeePayrollDetailsInDb.getDouble("days_of_leaves"),
						employeePayrollDetailsInDb.getDate("leaves_start_date"),
						employeePayrollDetailsInDb.getDate("leaves_end_date"),
						employeePayrollDetailsInDb.getString("loan_name"),
						employeePayrollDetailsInDb.getDouble("interest_rate"),
						employeePayrollDetailsInDb.getString("grade_level"),
						employeePayrollDetailsInDb.getDouble("allowances"),
						employeePayrollDetailsInDb.getDouble("initial_salary"),
						employeePayrollDetailsInDb.getDouble("upgrade_salary"),
						employeePayrollDetailsInDb.getString("employment_type")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (employeePayrollDetailsInDb != null)
				try {
					employeePayrollDetailsInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return employeePayrollDetailsList;
	}

	public static List<EmployeePayrollDetails> getAllEmployeePayrollDetails() {
		return getEmployeePayrollDetailsByQuery("SELECT * FROM EMPLOYEES_PAYROLL_DETAIL ORDER BY emp_payroll_id desc");
	}

	public static EmployeePayrollDetails getAllEmployeePayrollDetailsByEmployeeCode(String employeeCode) {
		List<EmployeePayrollDetails> employeePayrollDetails =getEmployeePayrollDetailsByQuery("SELECT * FROM EMPLOYEES_PAYROLL_DETAIL WHERE EMPLOYEE_CODE='"+employeeCode+"'"); 
		return (employeePayrollDetails!=null && employeePayrollDetails.size()>0)? employeePayrollDetails.get(0):null;
	}

	public static void updateEmployeePayrollDetails(EmployeePayrollDetails employeePayrollDetails) {
		String query = "UPDATE EMPLOYEES_PAYROLL_DETAIL SET "
				+ "department='"+ employeePayrollDetails.getDepartment() + "',"
				+ "sick_leaves="+ employeePayrollDetails.getSickLeaves() + ","
				+ "casual_leaves="+ employeePayrollDetails.getCasualLeaves() + ","
				+ "annual_leaves="+ employeePayrollDetails.getAnnualLeaves() + ","
				+ "study_leaves="+ employeePayrollDetails.getStudyLeaves() + ","
				+ "other="+ employeePayrollDetails.getOther() + ","
				+ "days_of_leaves="+ employeePayrollDetails.getDaysOfLeaves() + ","
				+ "leaves_start_date='"+ employeePayrollDetails.getLeaveStartDate() + "',"
				+ "leaves_end_date='"+ employeePayrollDetails.getLeaveEndDate() + "',"
				+ "loan_name='"+ employeePayrollDetails.getLoanName() + "',"
				+ "interest_rate="+ employeePayrollDetails.getIntertestRate() + ","
				+ "grade_level='"+ employeePayrollDetails.getGradeLevel() + "',"
				+ "allowances="+ employeePayrollDetails.getAllowances() + ","
				+ "initial_salary="+ employeePayrollDetails.getInitialSalary() + ","
				+ "upgrade_salary="+ employeePayrollDetails.getUpgradeSalary() + ","
				+ "employment_type='"+ employeePayrollDetails.getEmploymentType() + "' WHERE emp_payroll_id="+employeePayrollDetails.getId();
		jdbcTemplate.executeUpdate(query);

	}
}
