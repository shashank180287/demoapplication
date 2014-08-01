package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.ProductSupply;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.ProductSupplyDetailsRepository;
import com.mobile.tool.stock.manager.repository.ProductSupplyRepository;

public class ProductOrdersOptionHandler {


	public static void handleProductSupplyOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(ProductSupply.getTableModel(
				ProductSupplyRepository.getAllProductSupply()), 
				ProductSupply.tableColumnNames);
	}

	public static void handleStockManageOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(ProductSupply.getTableModelForCurrentStock(
				ProductSupplyDetailsRepository.getCurrentStockByProduct()), 
				ProductSupply.currentStockColumnNames);
	}
}
