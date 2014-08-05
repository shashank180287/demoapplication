package com.mobile.tool.stock.manager.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.mobile.tool.stock.manager.entity.SalesRecord;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.OwerDetailsRepository;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;

public class RemoveOwerDetailsRecordListener extends JFrame implements ActionListener {
	private static final long serialVersionUID = 6191510576296067659L;

	StockManagementTableModel tableModel;
	JTable table;
	private AdminRoleMouseListener listener;

	public RemoveOwerDetailsRecordListener(JTable table, StockManagementTableModel tableModel, AdminRoleMouseListener listener) {
		this.listener = listener;
		this.table = table;
		this.tableModel = tableModel;
	}

	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			Object id = tableModel.getValueAt(table.getSelectedRow(), 0);
			final long idStr = (id!=null)?Long.parseLong(id.toString()):0L;
			Object salesCode = tableModel.getValueAt(table.getSelectedRow(), 1);
			String salesCodeStr = (salesCode!=null)?salesCode.toString():"";
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete the selected record?", "Alert", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				OwerDetailsRepository.removeOwerDetails(idStr);
				
				if(salesCodeStr!=null){
					SalesRecord salesRecord = SaleRecordsRepository.getSaleRecordBySalesCode(salesCodeStr);
					if(salesRecord!=null){
						SaleRecordsRepository.clearBalanceBySaleCode(salesCodeStr);
					}
				}
				
				listener.reloadOwerListDetailTableData();
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
}
