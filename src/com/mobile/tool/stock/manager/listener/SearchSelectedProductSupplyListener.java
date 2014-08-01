package com.mobile.tool.stock.manager.listener;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mobile.tool.stock.manager.entity.ProductSupplyDetails;
import com.mobile.tool.stock.manager.model.StockManagementTableModel;
import com.mobile.tool.stock.manager.repository.ProductSupplyDetailsRepository;

public class SearchSelectedProductSupplyListener extends JFrame implements
		MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	StockManagementTableModel model;
	JTable customerTable;
	JScrollPane pane;
	
	String supplyProductColumns[] = {"Product Name", "No of Items"};
	private JTable table;
	private DefaultTableModel tableModel;
	
	public SearchSelectedProductSupplyListener(JTable table,
			DefaultTableModel tableModel) throws HeadlessException {
		super();
		this.table = table;
		this.tableModel = tableModel;
	}

	public void createAndShowGUI() {
		if(table.getSelectedRow()!=-1){
			String supplyCode = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
			List<ProductSupplyDetails> productSupplyDetails = ProductSupplyDetailsRepository.getProductSupplyBySupplyCode(supplyCode);
			model = new StockManagementTableModel(getTableModelArray(productSupplyDetails), supplyProductColumns);
			customerTable = new JTable(model) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					return false;
				}
			};
			pane = new JScrollPane(customerTable);
			add(pane);
			revalidate();
			table.revalidate();
			pane.revalidate();
			model.refreshTableData(getTableModelArray(productSupplyDetails), supplyProductColumns);
		}
		setVisible(true);
		setSize(500, 400);
		setLayout(new FlowLayout());
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				if(model!=null){
					for (int i = 1; i <= model.getRowCount(); i++) {
						model.removeRow(i-1);
					}
				}
				model = null;;
				customerTable = null;
				pane = null;		
				dispose();
			}
		
		});
	}

	private String[][] getTableModelArray(List<ProductSupplyDetails> productSupplyDetails) {
		List<String[]> products = new ArrayList<>();
		for (ProductSupplyDetails productSupplyDetail : productSupplyDetails) {
			products.add(new String[]{productSupplyDetail.getProduct().getName(), productSupplyDetail.getCurrentStock()+""});
		}
		String[][] suppliedProductArray = new String[products.size()][];
		products.toArray(suppliedProductArray);
		return suppliedProductArray;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		createAndShowGUI();		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
