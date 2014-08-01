package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.ProductCatagory;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.ProductCategoryRepository;

public class ProductCategoryOptionHandler {

	public static void handleProductCategoryOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(ProductCatagory
				.getTableModel(ProductCategoryRepository
						.getAllProductCategories()),
				ProductCatagory.tableColumnNames);
	}

}
