package com.mobile.tool.stock.manager.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.handler.CustomerDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.SalesHistoryOptionHandler;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;


public class CustomerRoleMouseListener  extends MouseAdapter {

	private JTree tree;
	private StockManagementTableModel tableModel;
	private UserLoginDetails userLoginDetails;
	private String lastSelection;
	public CustomerRoleMouseListener() {
	}

	public CustomerRoleMouseListener(JTree tree,
			StockManagementTableModel tableModel,
			UserLoginDetails userLoginDetails) {
		super();
		this.tree = tree;
		this.tableModel = tableModel;
		this.userLoginDetails = userLoginDetails;
	}

	public void mouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		
		if ("Sales History".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Sales History".equalsIgnoreCase(lastSelection)) {
				SalesHistoryOptionHandler.handleSalesHistoryOptionSelectionForCustomer(
						tableModel, userLoginDetails);
				this.lastSelection = "Sales History";
			}
		} else if("Personal Details".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Personal Details".equalsIgnoreCase(lastSelection)) {
				CustomerDetailsOptionHandler.handleCustomerDetailsOptionSelection(
						tableModel, userLoginDetails);
				this.lastSelection = "Personal Details";
			} 
		}

	}
	
	public void reloadTableData() {
		SalesHistoryOptionHandler.handleSalesHistoryOptionSelection(
				tableModel, userLoginDetails);
	}
}
