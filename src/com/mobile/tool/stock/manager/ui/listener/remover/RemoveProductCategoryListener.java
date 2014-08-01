package com.mobile.tool.stock.manager.ui.listener.remover;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.mobile.tool.stock.manager.listener.AdminRoleMouseListener;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.ProductCategoryRepository;
import com.mobile.tool.stock.manager.ui.listener.StockManagerActionListener;

public class RemoveProductCategoryListener  extends JFrame implements StockManagerActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	private StockManagementTableModel tableModel;
	private JTable table;
	private AdminRoleMouseListener listener;
	
	public RemoveProductCategoryListener(StockManagementTableModel tableModel,
			JTable table, AdminRoleMouseListener listener)
			throws HeadlessException {
		super();
		this.tableModel = tableModel;
		this.table = table;
		this.listener = listener;
	}

	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			Object id = tableModel.getValueAt(table.getSelectedRow(), 0);
			final String idStr = (id!=null)?id.toString():"0";
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete the selected record?", "Alert", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				ProductCategoryRepository.removeProductCategoryDetails(Integer.parseInt(idStr));
				listener.reloadProductCategoryDetailsTable();
				dispose();
			}else{
				dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this,"Please select one record.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createAndShowGUI();

	}
	
	@Override
	public String getActionName() {
		return "Remove Product Category";
	}
}
