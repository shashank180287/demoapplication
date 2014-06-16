package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.RecordKeeping;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.RecordKeepingRepository;

public class RecordKeepingOptionHandler {

	
	public static void handleRecordKeeping(StockManagementTableModel tableModel) {
		tableModel.refreshTableData(RecordKeeping.getTableModel(RecordKeepingRepository.getAllRecordKeeping()), 
				RecordKeeping.tableColumnNames);
	}
}
