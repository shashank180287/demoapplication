package com.mobile.tool.stock.manager.listener;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.handler.CustomerDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.EmployeeDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.ProductDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.RecordKeepingOptionHandler;
import com.mobile.tool.stock.manager.handler.SalesHistoryOptionHandler;
import com.mobile.tool.stock.manager.handler.VendorDetailsOptionHandler;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;

public class AdminRoleMouseListener  extends MouseAdapter {

	private JTree tree;
	private StockManagementTableModel tableModel;
	private UserLoginDetails userLoginDetails;
	private String lastSelection;
	private JPanel btnPnl;
	private JButton button;
	
	public AdminRoleMouseListener() {
	}

	public AdminRoleMouseListener(JTree tree,
			StockManagementTableModel tableModel,
			UserLoginDetails userLoginDetails) {
		super();
		this.tree = tree;
		this.tableModel = tableModel;
		this.userLoginDetails = userLoginDetails;
	}

	public AdminRoleMouseListener addButtonPanel(JPanel btnPnl) {
		this.btnPnl = btnPnl;
		return this;
	}
	
	public void mouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		
		if ("Sales History".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Sales History".equalsIgnoreCase(lastSelection)) {
				SalesHistoryOptionHandler.handleSalesHistoryOptionSelectionForToday(
						tableModel);
				if(button!=null){
					btnPnl.remove(button);
					btnPnl.revalidate();
					btnPnl.repaint();
				}
				this.lastSelection = "Sales History";
			}
		} else if("Manage Records".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Manage Records".equalsIgnoreCase(lastSelection)) {
				RecordKeepingOptionHandler.handleRecordKeeping(	tableModel);
				addButtonsInPanel("Add Records", new AddRecordKeepingListener(this));
				this.lastSelection = "Manage Records";
			} 
		} else if("Customer List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Customer List".equalsIgnoreCase(lastSelection)) {
				CustomerDetailsOptionHandler.handleCustomerDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("New Customer", new AddCustomerRecordListener(this));
				this.lastSelection = "Customer List";
			} 
		} else if("Employee List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Employee List".equalsIgnoreCase(lastSelection)) {
				EmployeeDetailsOptionHandler.handleEmployeeDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("New Employee", new AddCustomerRecordListener(this));
				this.lastSelection = "Employee List";
			} 
		}else if("Vendor List".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Vendor List".equalsIgnoreCase(lastSelection)) {
				VendorDetailsOptionHandler.handleVendorDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("New Vendor", new AddCustomerRecordListener(this));
				this.lastSelection = "Vendor List";
			} 
		}else if("Product Order".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Product Order".equalsIgnoreCase(lastSelection)) {
				ProductDetailsOptionHandler.handleProductDetailsOptionSelectionForAdmin(
						tableModel);
				addButtonsInPanel("Add Product", new AddCustomerRecordListener(this));
				this.lastSelection = "Product Order";
			} 
		}

	}
	
	public void reloadCustomerTableData() {
		CustomerDetailsOptionHandler.handleCustomerDetailsOptionSelectionForAdmin(
				tableModel);
	}
	
	private void addButtonsInPanel(String buttonName, ActionListener actionListener) {
		if (btnPnl != null) {
			if(button!=null)
				btnPnl.remove(button);
			button = (new JButton(buttonName));
			button.addActionListener(actionListener);
			btnPnl.add(button);
			btnPnl.revalidate();
		}
	}

	public void reloadRecordKeepingTableData() {
		RecordKeepingOptionHandler.handleRecordKeeping(tableModel);
	}
}
