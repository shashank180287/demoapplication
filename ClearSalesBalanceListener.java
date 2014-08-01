package com.mobile.tool.stock.manager.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;

public class ClearSalesBalanceListener extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3400843212957787160L;
	WindowAdapter adapter;
	private StockManagementTableModel tableModel;
	private JTable table;
	private AdminRoleMouseListener adminListener;
	private EmployeeRoleMouseListener employeeListener;

	public ClearSalesBalanceListener(JTable table, StockManagementTableModel tableModel, AdminRoleMouseListener listener) {
		this.adminListener = listener;
		this.table = table;
		this.tableModel = tableModel;
	}

	public ClearSalesBalanceListener(JTable table, StockManagementTableModel tableModel, EmployeeRoleMouseListener listener) {
		this.employeeListener = listener;
		this.table = table;
		this.tableModel = tableModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		createAndShowGUI();
	}

	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			Object saleCode = tableModel.getValueAt(table.getSelectedRow(), 0);
			final String saleCodeStr = (saleCode!=null)?saleCode.toString():"0";

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to clear balance for the selected sale?", "Alert", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				SaleRecordsRepository.clearBalanceBySaleCode(saleCodeStr);
				if(adminListener!=null)
					adminListener.reloadOwerListDetailTableData();
				else
					employeeListener.reloadOwerListDetailTableData();
				dispose();
			}else{
				dispose();
			}
			addWindowListener(adapter);
		} else {
			JOptionPane.showMessageDialog(this,"Please select one record.");
		}
	}
}
