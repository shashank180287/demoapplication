package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.VendorRecord;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.VendorRecordRepository;

public class VendorDetailsOptionHandler {


	public static void handleVendorDetailsOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(VendorRecord.getTableModel(
				VendorRecordRepository.getAllVendorRecords()), 
				VendorRecord.tableColumnNames);
	}
}
