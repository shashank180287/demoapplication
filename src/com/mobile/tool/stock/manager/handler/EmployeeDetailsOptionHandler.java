package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.EmployeeRecord;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;

public class EmployeeDetailsOptionHandler {

	
	public static void handleEmployeeDetailsOptionSelection(StockManagementTableModel tableModel,UserLoginDetails userLoginDetails) {
		tableModel.refreshTableData(EmployeeRecord.getTableModel(
				EmployeeRecordsRepository.getEmployeeRecordByUsername(userLoginDetails.getUsername())), 
				EmployeeRecord.attributeColumnNames);
	}

	public static void handleEmployeeDetailsOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(EmployeeRecord.getTableModel(
				EmployeeRecordsRepository.getAllEmployeeRecords()), 
				EmployeeRecord.tableColumnNames);
	}
	
	public static void handleEmployeeDetailsOptionSelectionForManager(
			StockManagementTableModel tableModel, String managerUsername) {
		tableModel.refreshTableData(EmployeeRecord.getTableModel(
				EmployeeRecordsRepository.getEmployeeRecordsByManagerUserName(managerUsername)), 
				EmployeeRecord.tableColumnNames);
	}
}
