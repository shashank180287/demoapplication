package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.UserLoginDetailsRepository;

public class UserLoginDetailsOptionHandler {


	public static void handleUserLoginDetailsOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(UserLoginDetails.getTableModel(
				UserLoginDetailsRepository.getUserLoginDetails()), 
				UserLoginDetails.tableColumnNames);
	}
}
