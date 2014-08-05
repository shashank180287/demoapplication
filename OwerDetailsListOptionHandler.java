package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.OwerDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.OwerDetailsRepository;

public class OwerDetailsListOptionHandler {


	public static void handleOwerListOptionSelectionForAdmin(
			StockManagementTableModel tableModel) {
		tableModel.refreshTableData(OwerDetails.getTableModel(
				OwerDetailsRepository.getAllOwerDetails()), 
				OwerDetails.tableColumnNames);
	}
}
