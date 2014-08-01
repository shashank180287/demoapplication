package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.VendorCategory;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.VendorCategoryRepository;

public class VendorCategoryOptionHandler {

	public static void handleVendorCategoryOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(VendorCategory
				.getTableModel(VendorCategoryRepository
						.getAllVendorCategories()),
				VendorCategory.tableColumnNames);
	}

}
