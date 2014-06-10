package com.mobile.tool.stock.manager.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.mobile.tool.stock.manager.entity.UserLoginDetails;
import com.mobile.tool.stock.manager.handler.EmployeeDetailsOptionHandler;
import com.mobile.tool.stock.manager.handler.SalesHistoryOptionHandler;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;

public class EmployeeRoleMouseListener extends MouseAdapter {

	private JTree tree;
	private StockManagementTableModel tableModel;
	private UserLoginDetails userLoginDetails;
	private JPanel btnPnl;
	private String lastSelection;
	private JButton newSalesButton;
	public EmployeeRoleMouseListener() {
	}

	public EmployeeRoleMouseListener(JTree tree,
			StockManagementTableModel tableModel,
			UserLoginDetails userLoginDetails) {
		super();
		this.tree = tree;
		this.tableModel = tableModel;
		this.userLoginDetails = userLoginDetails;
	}

	public EmployeeRoleMouseListener addButtonPanel(JPanel btnPnl) {
		this.btnPnl = btnPnl;
		return this;
	}

	public void mouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		
		if ("Sales History".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Sales History".equalsIgnoreCase(lastSelection)) {
				SalesHistoryOptionHandler.handleSalesHistoryOptionSelection(
						tableModel, userLoginDetails);
				addButtonsInPanel();
				this.lastSelection = "Sales History";
			}
		} else if("Personal Details".equalsIgnoreCase(tp.getLastPathComponent()
				.toString())){
			if(!"Personal Details".equalsIgnoreCase(lastSelection)) {
				EmployeeDetailsOptionHandler.handleEmployeeDetailsOptionSelection(
						tableModel, userLoginDetails);
				if(newSalesButton!=null){
					btnPnl.remove(newSalesButton);
					btnPnl.revalidate();
					btnPnl.repaint();
				}
				this.lastSelection = "Personal Details";
			} 
		}

	}

	private void addButtonsInPanel() {
		if (btnPnl != null) {
			newSalesButton = (new JButton("New Sale"));
			newSalesButton.addActionListener(new AddingNewSalesRecordListener(this, userLoginDetails));
			btnPnl.add(newSalesButton);
			btnPnl.revalidate();
		}
	}
	
	public void reloadTableData() {
		SalesHistoryOptionHandler.handleSalesHistoryOptionSelection(
				tableModel, userLoginDetails);
	}
}
