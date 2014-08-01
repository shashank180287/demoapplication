package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;
import com.mobile.tool.stock.manager.repository.EmployeeRecordsRepository;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;

public class SalesHistoryOptionHandler {

	
	public static void handleSalesHistoryOptionSelection(StockManagementTableModel tableModel,UserLoginDetails userLoginDetails) {
		tableModel.refreshTableData(SalesRecord.getTableModel(
				SaleRecordsRepository.getSaleRecordForEmployee(
						EmployeeRecordsRepository.getEmployeeRecordByUsername(userLoginDetails.getUsername()).getEmployeeCode())), 
				SalesRecord.tableColumnNames);
	}

	public static void handleSalesHistoryOptionSelectionForCustomer(
			StockManagementTableModel tableModel,
			UserLoginDetails userLoginDetails) {
		tableModel.refreshTableData(SalesRecord.getTableModel(
				SaleRecordsRepository.getSaleRecordForCustomer(
				CustomerRecordsRepository.getCustomerRecordByByUsername(userLoginDetails.getUsername()).getCustomerCode())), 
		SalesRecord.tableColumnNames);
	}

	public static void handleSalesHistoryOptionSelectionForToday(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(SalesRecord.getTableModel(
				SaleRecordsRepository.getSaleRecordForToday()), 
		SalesRecord.tableColumnNames);
		
	}
	
	public static void handleSalesHistoryOptionSelectionForManager(
			StockManagementTableModel tableModel,
			String managerUsername) {
		tableModel.refreshTableData(SalesRecord.getTableModel(
				SaleRecordsRepository.getSaleRecordForManagerSupervisee(managerUsername)), 
				SalesRecord.tableColumnNames);
	}
}
