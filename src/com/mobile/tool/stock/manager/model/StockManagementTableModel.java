package com.mobile.tool.stock.manager.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class StockManagementTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3821481905194303238L;
	private Integer editableRowNo;
	private List<Integer> skipColumns;
	
	public StockManagementTableModel(String[][] tableData, String[] columnNames) {
		super(tableData, columnNames);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if(row == editableRowNo && (skipColumns==null || !skipColumns.contains(column)))
			return true;
		return false;
	}
	
	public void refreshTableData(String[][] tableData, String[] columnNames) {
		for (int i = 0; i < this.getRowCount(); i++) {
			this.removeRow(i);
		}
		this.setColumnCount(columnNames.length);
		this.setDataVector(tableData	, columnNames);
		this.fireTableStructureChanged();
	}

	public void addEditableRow(int rowNo, List<Integer> skipColumns) {
		this.editableRowNo = rowNo;
		this.skipColumns = skipColumns;
	}
	
	public void removeEditableRow() {
		this.editableRowNo = null;
	}
}
