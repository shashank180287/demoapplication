package com.mobile.tool.stock.manager.handler;

import com.mobile.tool.stock.manager.entity.VisitorEnquiryDetail;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.VisitorEnquiryDetailRepository;

public class VisitorEnquiryDetailOptionHandler {

	
	public static void handleVisitorEnquiryDetails(StockManagementTableModel tableModel) {
		tableModel.refreshTableData(VisitorEnquiryDetail.getTableModel(VisitorEnquiryDetailRepository.getAllVisitorEnquiryDetails()), 
				VisitorEnquiryDetail.tableColumnNames);
	}
}
