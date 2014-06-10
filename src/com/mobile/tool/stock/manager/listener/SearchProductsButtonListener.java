package com.mobile.tool.stock.manager.listener;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mobile.tool.stock.manager.entity.ProductRecord;
import com.mobile.tool.stock.manager.repository.ProductRecordRepository;

public class SearchProductsButtonListener extends JFrame implements
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	DefaultTableModel model;
	JTable productTable;
	String searchProductColumns[] = { "Product Code", "Name", "Vendor" };
	private List<ProductRecord> productRecords;
	
	private final AddingNewSalesRecordListener listener;
	
	public SearchProductsButtonListener(AddingNewSalesRecordListener listener) {
		this.listener = listener;
	}
	
	public void createAndShowGUI() {
		productRecords = ProductRecordRepository.getAllProductRecords();
		model = new DefaultTableModel( getTableModelArray(productRecords), searchProductColumns);
		productTable = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		productTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2 && productRecords!=null && productRecords.size()>=row) {
		            listener.setProductText(productRecords.get(row).getProductCode());
		            closeWindow();
		        }
		    }
		});
		JScrollPane pane = new JScrollPane(productTable);
		add(pane);
		setVisible(true);
		setSize(500, 400);
		setLayout(new FlowLayout());
	}

	private String[][] getTableModelArray(List<ProductRecord> allProductRecords) {
		List<String[]> products = new ArrayList<>();
		for (ProductRecord productRecord : allProductRecords) {
			products.add(new String[]{productRecord.getProductCode(), productRecord.getName(), productRecord.getProductcategory().getCategoryName()});
		}
		String[][] productsArray = new String[products.size()][];
		products.toArray(productsArray);
		return productsArray;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this.frame.setEnabled(false);
		createAndShowGUI();

	}
	
	public void closeWindow() {
		setVisible(false);
		dispose();
	}
}
