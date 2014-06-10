package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.CustomerRecord;
import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.CustomerRecordsRepository;

public class CustomerDetailsOptionHandler {

	
	public static void handleCustomerDetailsOptionSelection(StockManagementTableModel tableModel,UserLoginDetails userLoginDetails) {
		tableModel.refreshTableData(CustomerRecord.getTableModel(
				CustomerRecordsRepository.getCustomerRecordByByUsername(userLoginDetails.getUsername())), 
				CustomerRecord.attributeColumnNames);
	}
}
