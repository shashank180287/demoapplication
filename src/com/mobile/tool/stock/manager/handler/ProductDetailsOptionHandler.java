package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;

public class ProductDetailsOptionHandler {


	public static void handleProductDetailsOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(ProductRecord.getTableModel(
				ProductRecordRepository.getAllProductRecords()), 
				ProductRecord.tableColumnNames);
	}
}
